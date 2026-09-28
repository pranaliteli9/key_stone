package com.key_stone.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.WorkOrderResponseDTO;
import com.key_stone.DTO.WorkOrderStatusRequestDTO;
import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Service.WorkOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technician/work-orders")
public class WorkOrderController {

	private final WorkOrderService workOrderService;

	public WorkOrderController(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	// Get all work orders assigned to logged-in technician
	@GetMapping
	public ResponseEntity<List<WorkOrderResponseDTO>> getMyWorkOrders(Authentication authentication) {

		String email = authentication.getName();

		List<WorkOrder> workOrders = workOrderService.getMyWorkOrders(email);

		List<WorkOrderResponseDTO> response = workOrders.stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(response);
	}

	// Get one work order
	@GetMapping("/{id}")
	public ResponseEntity<WorkOrderResponseDTO> getMyWorkOrder(@PathVariable Long id, Authentication authentication) {

		String email = authentication.getName();

		WorkOrder workOrder = workOrderService.getMyWorkOrder(id, email);

		return ResponseEntity.ok(convertToDTO(workOrder));
	}

	// Update work order status
	@PostMapping("/{id}/status")
	public ResponseEntity<WorkOrderResponseDTO> updateStatus(@PathVariable Long id,
			@Valid @RequestBody WorkOrderStatusRequestDTO dto, Authentication authentication) {

		String email = authentication.getName();

		WorkOrder updatedWorkOrder = workOrderService.updateStatus(id, email, dto.getStatus());

		return ResponseEntity.ok(convertToDTO(updatedWorkOrder));
	}

	// Convert Entity to DTO
	private WorkOrderResponseDTO convertToDTO(WorkOrder workOrder) {

		User technician = workOrder.getTechnician();

		Long technicianId = null;
		String technicianName = null;
		String technicianEmail = null;
		String technicianRole = null;

		if (technician != null) {
			technicianId = technician.getId();
			technicianName = technician.getName();
			technicianEmail = technician.getEmail();
			technicianRole = technician.getRole().name();
		}

		return new WorkOrderResponseDTO(workOrder.getId(), workOrder.getTitle(), workOrder.getDescription(),
				workOrder.getLocation(), workOrder.getStatus(), technicianId, technicianName, technicianEmail,
				technicianRole);
	}
}