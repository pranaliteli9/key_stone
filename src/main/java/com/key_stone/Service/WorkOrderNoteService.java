package com.key_stone.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderNote;
import com.key_stone.DTO.WorkOrderNoteRequestDTO;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderNoteRepository;
import com.key_stone.Repository.WorkOrderRepository;

@Service
public class WorkOrderNoteService {

	private final WorkOrderNoteRepository noteRepository;

	private final WorkOrderRepository workOrderRepository;

	private final UserRepository userRepository;

	public WorkOrderNoteService(WorkOrderNoteRepository noteRepository, WorkOrderRepository workOrderRepository,
			UserRepository userRepository) {

		this.noteRepository = noteRepository;
		this.workOrderRepository = workOrderRepository;
		this.userRepository = userRepository;
	}

	// Add note to work order
	public WorkOrderNote addNote(Long workOrderId, String email, WorkOrderNoteRequestDTO dto) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Find work order
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		checkTechnicianAccess(workOrder, technician);

		// Create note
		WorkOrderNote workOrderNote = new WorkOrderNote();

		workOrderNote.setWorkOrder(workOrder);

		workOrderNote.setTechnician(technician);

		workOrderNote.setNote(dto.getNote());

		workOrderNote.setCreatedAt(LocalDateTime.now());

		return noteRepository.save(workOrderNote);
	}

	// Get all notes of a work order
	public List<WorkOrderNote> getNotes(Long workOrderId, String email) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Find work order
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		checkTechnicianAccess(workOrder, technician);

		// Get notes
		return noteRepository.findByWorkOrderOrderByCreatedAtAsc(workOrder);
	}

	// Check technician access
	private void checkTechnicianAccess(WorkOrder workOrder, User technician) {

		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to access notes for this work order");
		}
	}
}