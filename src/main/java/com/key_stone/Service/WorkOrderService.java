package com.key_stone.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderStatusHistory;
import com.key_stone.Enum.WorkOrderStatus;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderRepository;
import com.key_stone.Repository.WorkOrderStatusHistoryRepository;

@Service
public class WorkOrderService {

	private final WorkOrderRepository workOrderRepository;
	private final UserRepository userRepository;
	private final WorkOrderStatusHistoryRepository historyRepository;

	public WorkOrderService(WorkOrderRepository workOrderRepository, UserRepository userRepository,
			WorkOrderStatusHistoryRepository historyRepository) {

		this.workOrderRepository = workOrderRepository;
		this.userRepository = userRepository;
		this.historyRepository = historyRepository;
	}

	// Get all work orders assigned to logged-in technician
	public List<WorkOrder> getMyWorkOrders(String email) {

		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		return workOrderRepository.findByTechnician(technician);
	}

	// Get one work order assigned to logged-in technician
	public WorkOrder getMyWorkOrder(Long workOrderId, String email) {

		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		checkTechnicianAccess(workOrder, technician);

		return workOrder;
	}

	// Update work order status and save history
	public WorkOrder updateStatus(Long workOrderId, String email, WorkOrderStatus newStatus) {

		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		checkTechnicianAccess(workOrder, technician);

		WorkOrderStatus oldStatus = workOrder.getStatus();

		// Validate status transition
		if (!isValidTransition(oldStatus, newStatus)) {
			throw new RuntimeException("Invalid status transition from " + oldStatus + " to " + newStatus);
		}

		// Update work order status
		workOrder.setStatus(newStatus);

		WorkOrder savedWorkOrder = workOrderRepository.save(workOrder);

		// Create status history
		WorkOrderStatusHistory history = new WorkOrderStatusHistory();

		history.setWorkOrder(savedWorkOrder);
		history.setOldStatus(oldStatus);
		history.setNewStatus(newStatus);
		history.setChangedBy(technician);
		history.setChangedAt(LocalDateTime.now());

		historyRepository.save(history);

		return savedWorkOrder;
	}

	// Check technician access
	private void checkTechnicianAccess(WorkOrder workOrder, User technician) {

		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to access this work order");
		}
	}

	// Allowed status transitions
	private boolean isValidTransition(WorkOrderStatus currentStatus, WorkOrderStatus newStatus) {

		if (currentStatus == null || newStatus == null) {
			return false;
		}

		switch (currentStatus) {

		case NEW:
			return newStatus == WorkOrderStatus.ASSIGNED;

		case ASSIGNED:
			return newStatus == WorkOrderStatus.IN_PROGRESS;

		case IN_PROGRESS:
			return newStatus == WorkOrderStatus.ON_HOLD || newStatus == WorkOrderStatus.COMPLETED;

		case ON_HOLD:
			return newStatus == WorkOrderStatus.IN_PROGRESS;

		case COMPLETED:
			return false;

		case CLOSED:
			return false;

		case CANCELLED:
			return false;

		default:
			return false;
		}
	}
}