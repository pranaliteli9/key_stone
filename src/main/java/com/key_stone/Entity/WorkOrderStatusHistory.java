package com.key_stone.Entity;

import java.time.LocalDateTime;

import com.key_stone.Enum.WorkOrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "work_order_status_history")
public class WorkOrderStatusHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "work_order_id", nullable = false)
	private WorkOrder workOrder;

	@Enumerated(EnumType.STRING)
	@Column(name = "old_status", nullable = false)
	private WorkOrderStatus oldStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "new_status", nullable = false)
	private WorkOrderStatus newStatus;

	@ManyToOne
	@JoinColumn(name = "changed_by", nullable = false)
	private User changedBy;

	@Column(name = "changed_at", nullable = false)
	private LocalDateTime changedAt;

	public WorkOrderStatusHistory() {
	}

	public WorkOrderStatusHistory(Long id, WorkOrder workOrder, WorkOrderStatus oldStatus, WorkOrderStatus newStatus,
			User changedBy, LocalDateTime changedAt) {

		this.id = id;
		this.workOrder = workOrder;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.changedBy = changedBy;
		this.changedAt = changedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public WorkOrderStatus getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(WorkOrderStatus oldStatus) {
		this.oldStatus = oldStatus;
	}

	public WorkOrderStatus getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(WorkOrderStatus newStatus) {
		this.newStatus = newStatus;
	}

	public User getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}
}