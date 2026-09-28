package com.key_stone.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.WorkOrderNoteRequestDTO;
import com.key_stone.DTO.WorkOrderNoteResponseDTO;
import com.key_stone.Entity.WorkOrderNote;
import com.key_stone.Service.WorkOrderNoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technician/work-orders")
public class WorkOrderNoteController {

	private final WorkOrderNoteService noteService;

	public WorkOrderNoteController(WorkOrderNoteService noteService) {

		this.noteService = noteService;
	}

	// Add note
	@PostMapping("/{workOrderId}/notes")
	public ResponseEntity<WorkOrderNoteResponseDTO> addNote(

			@PathVariable Long workOrderId,

			@Valid @RequestBody WorkOrderNoteRequestDTO dto,

			Authentication authentication) {

		String email = authentication.getName();

		WorkOrderNote note = noteService.addNote(workOrderId, email, dto);

		return ResponseEntity.ok(convertToDTO(note));
	}

	// Get all notes
	@GetMapping("/{workOrderId}/notes")
	public ResponseEntity<List<WorkOrderNoteResponseDTO>> getNotes(

			@PathVariable Long workOrderId,

			Authentication authentication) {

		String email = authentication.getName();

		List<WorkOrderNote> notes = noteService.getNotes(workOrderId, email);

		List<WorkOrderNoteResponseDTO> response = notes.stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(response);
	}

	// Convert Entity to DTO
	private WorkOrderNoteResponseDTO convertToDTO(WorkOrderNote note) {

		return new WorkOrderNoteResponseDTO(

				note.getId(),

				note.getWorkOrder().getId(),

				note.getTechnician().getId(),

				note.getTechnician().getName(),

				note.getNote(),

				note.getCreatedAt());
	}
}