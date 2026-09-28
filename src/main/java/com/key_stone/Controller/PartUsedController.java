package com.key_stone.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.PartUsedRequestDTO;
import com.key_stone.DTO.PartUsedResponseDTO;
import com.key_stone.Entity.PartUsed;
import com.key_stone.Service.PartUsedService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technician/work-orders")
public class PartUsedController {

	private final PartUsedService partUsedService;

	public PartUsedController(PartUsedService partUsedService) {
		this.partUsedService = partUsedService;
	}

	// Add a part to a work order
	@PostMapping("/{workOrderId}/parts")
	public ResponseEntity<PartUsedResponseDTO> addPart(@PathVariable Long workOrderId,
			@Valid @RequestBody PartUsedRequestDTO dto, Authentication authentication) {

		String email = authentication.getName();

		PartUsed savedPart = partUsedService.addPart(workOrderId, email, dto);

		return ResponseEntity.ok(convertToDTO(savedPart));
	}

	// Get all parts used for a work order
	@GetMapping("/{workOrderId}/parts")
	public ResponseEntity<List<PartUsedResponseDTO>> getParts(@PathVariable Long workOrderId,
			Authentication authentication) {

		String email = authentication.getName();

		List<PartUsed> parts = partUsedService.getPartsByWorkOrder(workOrderId, email);

		List<PartUsedResponseDTO> response = parts.stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(response);
	}

	// Convert Entity to DTO
	private PartUsedResponseDTO convertToDTO(PartUsed partUsed) {

		return new PartUsedResponseDTO(partUsed.getId(), partUsed.getWorkOrder().getId(), partUsed.getPartName(),
				partUsed.getQuantity(), partUsed.getNotes());
	}
}