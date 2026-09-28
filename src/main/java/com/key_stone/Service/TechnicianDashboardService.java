package com.key_stone.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.key_stone.DTO.TechnicianDashboardResponseDTO;
import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Enum.WorkOrderStatus;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderRepository;

@Service
public class TechnicianDashboardService {

	private final UserRepository userRepository;

	private final WorkOrderRepository workOrderRepository;

	public TechnicianDashboardService(UserRepository userRepository, WorkOrderRepository workOrderRepository) {

		this.userRepository = userRepository;

		this.workOrderRepository = workOrderRepository;
	}

	// Get technician dashboard summary
	public TechnicianDashboardResponseDTO getDashboard(String email) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Get only this technician's work orders
		List<WorkOrder> workOrders = workOrderRepository.findByTechnician(technician);

		long totalWorkOrders = workOrders.size();

		long newWorkOrders = countByStatus(workOrders, WorkOrderStatus.NEW);

		long assignedWorkOrders = countByStatus(workOrders, WorkOrderStatus.ASSIGNED);

		long inProgressWorkOrders = countByStatus(workOrders, WorkOrderStatus.IN_PROGRESS);

		long onHoldWorkOrders = countByStatus(workOrders, WorkOrderStatus.ON_HOLD);

		long completedWorkOrders = countByStatus(workOrders, WorkOrderStatus.COMPLETED);

		return new TechnicianDashboardResponseDTO(totalWorkOrders, newWorkOrders, assignedWorkOrders,
				inProgressWorkOrders, onHoldWorkOrders, completedWorkOrders);
	}

	// Count work orders by status
	private long countByStatus(List<WorkOrder> workOrders, WorkOrderStatus status) {

		return workOrders.stream().filter(workOrder -> workOrder.getStatus() == status).count();
	}
}