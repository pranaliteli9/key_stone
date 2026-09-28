package com.key_stone.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderAttachment;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderAttachmentRepository;
import com.key_stone.Repository.WorkOrderRepository;

@Service
public class WorkOrderAttachmentService {

	private final WorkOrderAttachmentRepository attachmentRepository;

	private final WorkOrderRepository workOrderRepository;

	private final UserRepository userRepository;

	private final String uploadDirectory = "uploads/work-orders";

	public WorkOrderAttachmentService(WorkOrderAttachmentRepository attachmentRepository,
			WorkOrderRepository workOrderRepository, UserRepository userRepository) {

		this.attachmentRepository = attachmentRepository;
		this.workOrderRepository = workOrderRepository;
		this.userRepository = userRepository;
	}

	// Upload attachment
	public WorkOrderAttachment uploadAttachment(Long workOrderId, String email, MultipartFile file) throws IOException {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Find work order
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to upload attachment for this work order");
		}

		// Check empty file
		if (file == null || file.isEmpty()) {

			throw new RuntimeException("File cannot be empty");
		}

		// Create directory
		Path workOrderDirectory = Paths.get(uploadDirectory, String.valueOf(workOrderId));

		Files.createDirectories(workOrderDirectory);

		// Get original file name
		String originalFileName = file.getOriginalFilename();

		if (originalFileName == null || originalFileName.isBlank()) {

			throw new RuntimeException("Invalid file name");
		}

		// Generate unique file name
		String fileName = UUID.randomUUID() + "_" + originalFileName;

		// Full file path
		Path filePath = workOrderDirectory.resolve(fileName);

		// Save file to local folder
		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		// Save metadata in database
		WorkOrderAttachment attachment = new WorkOrderAttachment();

		attachment.setWorkOrder(workOrder);

		attachment.setUploadedBy(technician);

		attachment.setFileName(originalFileName);

		attachment.setFilePath(filePath.toString());

		attachment.setFileType(file.getContentType());

		attachment.setFileSize(file.getSize());

		attachment.setUploadedAt(LocalDateTime.now());

		return attachmentRepository.save(attachment);
	}

	// Get all attachments of a work order
	public List<WorkOrderAttachment> getAttachments(Long workOrderId, String email) {

		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to view attachments for this work order");
		}

		return attachmentRepository.findByWorkOrder(workOrder);
	}

	// Get attachment for download
	public WorkOrderAttachment getAttachmentForDownload(Long workOrderId, Long attachmentId, String email) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Find work order
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to download attachment for this work order");
		}

		// Find attachment
		WorkOrderAttachment attachment = attachmentRepository.findById(attachmentId)
				.orElseThrow(() -> new RuntimeException("Attachment not found"));

		// Check attachment belongs to this work order
		if (attachment.getWorkOrder() == null || !attachment.getWorkOrder().getId().equals(workOrderId)) {

			throw new RuntimeException("Attachment does not belong to this work order");
		}

		return attachment;
	}

	// Delete attachment
	public void deleteAttachment(Long workOrderId, Long attachmentId, String email) throws IOException {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Find work order
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to delete attachment for this work order");
		}

		// Find attachment
		WorkOrderAttachment attachment = attachmentRepository.findById(attachmentId)
				.orElseThrow(() -> new RuntimeException("Attachment not found"));

		// Check attachment belongs to this work order
		if (attachment.getWorkOrder() == null || !attachment.getWorkOrder().getId().equals(workOrderId)) {

			throw new RuntimeException("Attachment does not belong to this work order");
		}

		// Get physical file path
		Path filePath = Paths.get(attachment.getFilePath());

		// Delete actual file
		if (Files.exists(filePath)) {
			Files.delete(filePath);
		}

		// Delete database record
		attachmentRepository.delete(attachment);
	}
}