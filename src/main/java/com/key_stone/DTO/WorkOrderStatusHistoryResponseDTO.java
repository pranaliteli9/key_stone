package com.key_stone.DTO;

import java.time.LocalDateTime;

import com.key_stone.Enum.WorkOrderStatus;

public class WorkOrderStatusHistoryResponseDTO {

	private Long id;
	private Long workOrderId;
	private WorkOrderStatus oldStatus;
	private WorkOrderStatus newStatus;
	private Long changedBy;
	private String changedByName;
	private LocalDateTime changedAt;

	public WorkOrderStatusHistoryResponseDTO() {
	}

	public WorkOrderStatusHistoryResponseDTO(Long id, Long workOrderId, WorkOrderStatus oldStatus,
			WorkOrderStatus newStatus, Long changedBy, String changedByName, LocalDateTime changedAt) {

		this.id = id;
		this.workOrderId = workOrderId;
		this.oldStatus = oldStatus;
		this.newStatus = newStatus;
		this.changedBy = changedBy;
		this.changedByName = changedByName;
		this.changedAt = changedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
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

	public Long getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(Long changedBy) {
		this.changedBy = changedBy;
	}

	public String getChangedByName() {
		return changedByName;
	}

	public void setChangedByName(String changedByName) {
		this.changedByName = changedByName;
	}

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}
}