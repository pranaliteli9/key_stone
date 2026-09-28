package com.key_stone.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.WorkOrderTimeLogRequestDTO;
import com.key_stone.DTO.WorkOrderTimeLogResponseDTO;
import com.key_stone.Entity.WorkOrderTimeLog;
import com.key_stone.Service.WorkOrderTimeLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technician/work-orders")
public class WorkOrderTimeLogController {

	private final WorkOrderTimeLogService timeLogService;

	public WorkOrderTimeLogController(WorkOrderTimeLogService timeLogService) {

		this.timeLogService = timeLogService;
	}

	// Add time log to a work order
	@PostMapping("/{workOrderId}/time")
	public ResponseEntity<WorkOrderTimeLogResponseDTO> addTimeLog(@PathVariable Long workOrderId,
			@Valid @RequestBody WorkOrderTimeLogRequestDTO dto, Authentication authentication) {

		String email = authentication.getName();

		WorkOrderTimeLog savedTimeLog = timeLogService.addTimeLog(workOrderId, email, dto);

		return ResponseEntity.ok(convertToDTO(savedTimeLog));
	}

	// Get all time logs for a work order
	@GetMapping("/{workOrderId}/time")
	public ResponseEntity<List<WorkOrderTimeLogResponseDTO>> getTimeLogs(@PathVariable Long workOrderId,
			Authentication authentication) {

		String email = authentication.getName();

		List<WorkOrderTimeLog> timeLogs = timeLogService.getTimeLogsByWorkOrder(workOrderId, email);

		List<WorkOrderTimeLogResponseDTO> response = timeLogs.stream().map(this::convertToDTO).toList();

		return ResponseEntity.ok(response);
	}

	// Convert Entity to DTO
	private WorkOrderTimeLogResponseDTO convertToDTO(WorkOrderTimeLog timeLog) {

		return new WorkOrderTimeLogResponseDTO(timeLog.getId(), timeLog.getWorkOrder().getId(),
				timeLog.getTechnician().getId(), timeLog.getStartTime(), timeLog.getEndTime(),
				timeLog.getDurationMinutes(), timeLog.getNotes());
	}
}