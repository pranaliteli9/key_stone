package com.key_stone.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderStatusHistory;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderRepository;
import com.key_stone.Repository.WorkOrderStatusHistoryRepository;

@Service
public class WorkOrderStatusHistoryService {

	private final WorkOrderStatusHistoryRepository historyRepository;
	private final WorkOrderRepository workOrderRepository;
	private final UserRepository userRepository;

	public WorkOrderStatusHistoryService(WorkOrderStatusHistoryRepository historyRepository,
			WorkOrderRepository workOrderRepository, UserRepository userRepository) {

		this.historyRepository = historyRepository;
		this.workOrderRepository = workOrderRepository;
		this.userRepository = userRepository;
	}

	// Get status history of a work order
	public List<WorkOrderStatusHistory> getHistory(Long workOrderId, String email) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Find work order
		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to view this work order history");
		}

		// Return history in ascending order
		return historyRepository.findByWorkOrderOrderByChangedAtAsc(workOrder);
	}
}