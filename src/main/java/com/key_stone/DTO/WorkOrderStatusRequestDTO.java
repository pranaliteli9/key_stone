package com.key_stone.DTO;

import com.key_stone.Enum.WorkOrderStatus;

import jakarta.validation.constraints.NotNull;

public class WorkOrderStatusRequestDTO {

	@NotNull(message = "Status is required")
	private WorkOrderStatus status;

	public WorkOrderStatusRequestDTO() {
	}

	public WorkOrderStatusRequestDTO(WorkOrderStatus status) {
		this.status = status;
	}

	public WorkOrderStatus getStatus() {
		return status;
	}

	public void setStatus(WorkOrderStatus status) {
		this.status = status;
	}
}