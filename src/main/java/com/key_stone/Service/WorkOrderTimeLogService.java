package com.key_stone.Service;

import java.time.Duration;
import java.util.List;

import org.springframework.stereotype.Service;

import com.key_stone.DTO.WorkOrderTimeLogRequestDTO;
import com.key_stone.Entity.User;
import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderTimeLog;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Repository.WorkOrderRepository;
import com.key_stone.Repository.WorkOrderTimeLogRepository;

@Service
public class WorkOrderTimeLogService {

    private final WorkOrderTimeLogRepository timeLogRepository;
    private final WorkOrderRepository workOrderRepository;
    private final UserRepository userRepository;

    public WorkOrderTimeLogService(
            WorkOrderTimeLogRepository timeLogRepository,
            WorkOrderRepository workOrderRepository,
            UserRepository userRepository) {

        this.timeLogRepository = timeLogRepository;
        this.workOrderRepository = workOrderRepository;
        this.userRepository = userRepository;
    }

    // Add time log
    public WorkOrderTimeLog addTimeLog(
            Long workOrderId,
            String email,
            WorkOrderTimeLogRequestDTO dto) {

        User technician = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        WorkOrder workOrder = workOrderRepository.findById(workOrderId)
                .orElseThrow(() ->
                        new RuntimeException("Work Order not found"));

        // Check whether this work order belongs
        // to the logged-in technician
        checkTechnicianAccess(workOrder, technician);

        // Validate start and end time
        if (!dto.getEndTime().isAfter(dto.getStartTime())) {
            throw new RuntimeException(
                    "End time must be after start time");
        }

        // Calculate duration in minutes
        long durationMinutes = Duration.between(
                dto.getStartTime(),
                dto.getEndTime()
        ).toMinutes();

        if (durationMinutes <= 0) {
            throw new RuntimeException(
                    "Duration must be greater than zero");
        }

        WorkOrderTimeLog timeLog =
                new WorkOrderTimeLog();

        timeLog.setWorkOrder(workOrder);
        timeLog.setTechnician(technician);
        timeLog.setStartTime(dto.getStartTime());
        timeLog.setEndTime(dto.getEndTime());
        timeLog.setDurationMinutes(durationMinutes);
        timeLog.setNotes(dto.getNotes());

        return timeLogRepository.save(timeLog);
    }

    // Get time logs for a work order
    public List<WorkOrderTimeLog> getTimeLogsByWorkOrder(
            Long workOrderId,
            String email) {

        User technician = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        WorkOrder workOrder = workOrderRepository.findById(workOrderId)
                .orElseThrow(() ->
                        new RuntimeException("Work Order not found"));

        // Security check
        checkTechnicianAccess(workOrder, technician);

        return timeLogRepository.findByWorkOrder(workOrder);
    }

    // Technician access validation
    private void checkTechnicianAccess(
            WorkOrder workOrder,
            User technician) {

        if (workOrder.getTechnician() == null
                || !workOrder.getTechnician().getId()
                        .equals(technician.getId())) {

            throw new RuntimeException(
                    "You are not authorized to access this work order");
        }
    }
}