package com.key_stone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.key_stone.Entity.WorkOrder;
import com.key_stone.Entity.WorkOrderNote;
import com.key_stone.Entity.User;

@Repository
public interface WorkOrderNoteRepository extends JpaRepository<WorkOrderNote, Long> {

	List<WorkOrderNote> findByWorkOrderOrderByCreatedAtAsc(WorkOrder workOrder);

	List<WorkOrderNote> findByTechnicianOrderByCreatedAtDesc(User technician);
}