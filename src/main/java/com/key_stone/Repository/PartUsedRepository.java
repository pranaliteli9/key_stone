package com.key_stone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.key_stone.Entity.PartUsed;
import com.key_stone.Entity.WorkOrder;

@Repository
public interface PartUsedRepository extends JpaRepository<PartUsed, Long> {

	List<PartUsed> findByWorkOrder(WorkOrder workOrder);
}