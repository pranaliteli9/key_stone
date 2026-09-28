package com.key_stone.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.key_stone.DTO.PartUsedRequestDTO;
import com.key_stone.Entity.PartUsed;
import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Repository.PartUsedRepository;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderRepository;

@Service
public class PartUsedService {

	private final PartUsedRepository partUsedRepository;
	private final WorkOrderRepository workOrderRepository;
	private final UserRepository userRepository;

	public PartUsedService(PartUsedRepository partUsedRepository, WorkOrderRepository workOrderRepository,
			UserRepository userRepository) {

		this.partUsedRepository = partUsedRepository;
		this.workOrderRepository = workOrderRepository;
		this.userRepository = userRepository;
	}

	// Add a part to the logged-in technician's work order
	public PartUsed addPart(Long workOrderId, String email, PartUsedRequestDTO dto) {

		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check whether this work order belongs to logged-in technician
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to add parts to this work order");
		}

		PartUsed partUsed = new PartUsed();

		partUsed.setWorkOrder(workOrder);
		partUsed.setPartName(dto.getPartName());
		partUsed.setQuantity(dto.getQuantity());
		partUsed.setNotes(dto.getNotes());

		return partUsedRepository.save(partUsed);
	}

	// Get all parts used for a work order
	public List<PartUsed> getPartsByWorkOrder(Long workOrderId, String email) {

		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		WorkOrder workOrder = workOrderRepository.findById(workOrderId)
				.orElseThrow(() -> new RuntimeException("Work Order not found"));

		// Check technician access
		if (workOrder.getTechnician() == null || !workOrder.getTechnician().getId().equals(technician.getId())) {

			throw new RuntimeException("You are not authorized to view parts for this work order");
		}

		return partUsedRepository.findByWorkOrder(workOrder);
	}
}