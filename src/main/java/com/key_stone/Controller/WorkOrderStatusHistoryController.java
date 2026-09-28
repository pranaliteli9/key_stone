package com.key_stone.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.WorkOrderStatusHistoryResponseDTO;
import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrderStatusHistory;
import com.key_stone.Service.WorkOrderStatusHistoryService;

@RestController
@RequestMapping("/api/technician/work-orders")
public class WorkOrderStatusHistoryController {

	private final WorkOrderStatusHistoryService historyService;

	public WorkOrderStatusHistoryController(WorkOrderStatusHistoryService historyService) {

		this.historyService = historyService;
	}

	// Get status history of a work order
	@GetMapping("/{workOrderId}/history")
	public ResponseEntity<List<WorkOrderStatusHistoryResponseDTO>> getHistory(@PathVariable Long workOrderId,
			Authentication authentication) {

		String email = authentication.getName();

		List<WorkOrderStatusHistory> history = historyService.getHistory(workOrderId, email);

		List<WorkOrderStatusHistoryResponseDTO> response = history.stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(response);
	}

	// Convert Entity to DTO
	private WorkOrderStatusHistoryResponseDTO convertToDTO(WorkOrderStatusHistory history) {

		User changedBy = history.getChangedBy();

		Long changedById = null;
		String changedByName = null;

		if (changedBy != null) {
			changedById = changedBy.getId();
			changedByName = changedBy.getName();
		}

		return new WorkOrderStatusHistoryResponseDTO(history.getId(), history.getWorkOrder().getId(),
				history.getOldStatus(), history.getNewStatus(), changedById, changedByName, history.getChangedAt());
	}
}