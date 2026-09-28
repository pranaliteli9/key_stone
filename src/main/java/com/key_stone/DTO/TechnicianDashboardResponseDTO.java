package com.key_stone.DTO;

public class TechnicianDashboardResponseDTO {

	private Long totalWorkOrders;
	private Long newWorkOrders;
	private Long assignedWorkOrders;
	private Long inProgressWorkOrders;
	private Long onHoldWorkOrders;
	private Long completedWorkOrders;

	public TechnicianDashboardResponseDTO() {
	}

	public TechnicianDashboardResponseDTO(Long totalWorkOrders, Long newWorkOrders, Long assignedWorkOrders,
			Long inProgressWorkOrders, Long onHoldWorkOrders, Long completedWorkOrders) {

		this.totalWorkOrders = totalWorkOrders;
		this.newWorkOrders = newWorkOrders;
		this.assignedWorkOrders = assignedWorkOrders;
		this.inProgressWorkOrders = inProgressWorkOrders;
		this.onHoldWorkOrders = onHoldWorkOrders;
		this.completedWorkOrders = completedWorkOrders;
	}

	public Long getTotalWorkOrders() {
		return totalWorkOrders;
	}

	public void setTotalWorkOrders(Long totalWorkOrders) {
		this.totalWorkOrders = totalWorkOrders;
	}

	public Long getNewWorkOrders() {
		return newWorkOrders;
	}

	public void setNewWorkOrders(Long newWorkOrders) {
		this.newWorkOrders = newWorkOrders;
	}

	public Long getAssignedWorkOrders() {
		return assignedWorkOrders;
	}

	public void setAssignedWorkOrders(Long assignedWorkOrders) {
		this.assignedWorkOrders = assignedWorkOrders;
	}

	public Long getInProgressWorkOrders() {
		return inProgressWorkOrders;
	}

	public void setInProgressWorkOrders(Long inProgressWorkOrders) {
		this.inProgressWorkOrders = inProgressWorkOrders;
	}

	public Long getOnHoldWorkOrders() {
		return onHoldWorkOrders;
	}

	public void setOnHoldWorkOrders(Long onHoldWorkOrders) {
		this.onHoldWorkOrders = onHoldWorkOrders;
	}

	public Long getCompletedWorkOrders() {
		return completedWorkOrders;
	}

	public void setCompletedWorkOrders(Long completedWorkOrders) {
		this.completedWorkOrders = completedWorkOrders;
	}
}