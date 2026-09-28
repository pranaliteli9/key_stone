package com.key_stone.Controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.key_stone.DTO.AttachmentResponseDTO;
import com.key_stone.Entity.WorkOrderAttachment;
import com.key_stone.Service.WorkOrderAttachmentService;

@RestController
@RequestMapping("/api/technician/work-orders")
public class WorkOrderAttachmentController {

	private final WorkOrderAttachmentService attachmentService;

	public WorkOrderAttachmentController(WorkOrderAttachmentService attachmentService) {

		this.attachmentService = attachmentService;
	}

	// Upload attachment
	@PostMapping("/{workOrderId}/attachments")
	public ResponseEntity<AttachmentResponseDTO> uploadAttachment(

			@PathVariable Long workOrderId,

			@RequestParam("file") MultipartFile file,

			Authentication authentication) throws IOException {

		String email = authentication.getName();

		WorkOrderAttachment attachment = attachmentService.uploadAttachment(workOrderId, email, file);

		return ResponseEntity.ok(convertToDTO(attachment));
	}

	// Get all attachments
	@GetMapping("/{workOrderId}/attachments")
	public ResponseEntity<List<AttachmentResponseDTO>> getAttachments(

			@PathVariable Long workOrderId,

			Authentication authentication) {

		String email = authentication.getName();

		List<WorkOrderAttachment> attachments = attachmentService.getAttachments(workOrderId, email);

		List<AttachmentResponseDTO> response = attachments.stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(response);
	}

	// Download attachment
	@GetMapping("/{workOrderId}/attachments/{attachmentId}/download")
	public ResponseEntity<Resource> downloadAttachment(

			@PathVariable Long workOrderId,

			@PathVariable Long attachmentId,

			Authentication authentication) {

		String email = authentication.getName();

		WorkOrderAttachment attachment = attachmentService.getAttachmentForDownload(workOrderId, attachmentId, email);

		Path path = Paths.get(attachment.getFilePath());

		Resource resource = new FileSystemResource(path);

		if (!resource.exists() || !resource.isReadable()) {

			throw new RuntimeException("File not found");
		}

		String contentType = attachment.getFileType();

		if (contentType == null || contentType.isBlank()) {

			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
				.body(resource);
	}

	// Delete attachment
	@DeleteMapping("/{workOrderId}/attachments/{attachmentId}")
	public ResponseEntity<String> deleteAttachment(

			@PathVariable Long workOrderId,

			@PathVariable Long attachmentId,

			Authentication authentication) throws IOException {

		String email = authentication.getName();

		attachmentService.deleteAttachment(workOrderId, attachmentId, email);

		return ResponseEntity.ok("Attachment deleted successfully");
	}

	// Convert Entity to DTO
	private AttachmentResponseDTO convertToDTO(WorkOrderAttachment attachment) {

		return new AttachmentResponseDTO(

				attachment.getId(),

				attachment.getWorkOrder().getId(),

				attachment.getUploadedBy().getId(),

				attachment.getUploadedBy().getName(),

				attachment.getFileName(),

				attachment.getFilePath(),

				attachment.getFileType(),

				attachment.getFileSize(),

				attachment.getUploadedAt());
	}
}