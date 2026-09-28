package com.key_stone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderAttachment;

@Repository
public interface WorkOrderAttachmentRepository extends JpaRepository<WorkOrderAttachment, Long> {

	List<WorkOrderAttachment> findByWorkOrder(WorkOrder workOrder);
}