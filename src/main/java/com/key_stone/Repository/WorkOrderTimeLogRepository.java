package com.key_stone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderTimeLog;
import com.key_stone.Entity.User;

@Repository
public interface WorkOrderTimeLogRepository extends JpaRepository<WorkOrderTimeLog, Long> {

	List<WorkOrderTimeLog> findByWorkOrder(WorkOrder workOrder);

	List<WorkOrderTimeLog> findByTechnician(User technician);
}