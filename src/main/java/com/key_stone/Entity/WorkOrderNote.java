package com.key_stone.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "work_order_notes")
public class WorkOrderNote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "work_order_id", nullable = false)
	private WorkOrder workOrder;

	@ManyToOne
	@JoinColumn(name = "technician_id", nullable = false)
	private User technician;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String note;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	public WorkOrderNote() {
	}

	public WorkOrderNote(Long id, WorkOrder workOrder, User technician, String note, LocalDateTime createdAt) {

		this.id = id;
		this.workOrder = workOrder;
		this.technician = technician;
		this.note = note;
		this.createdAt = createdAt;
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

	public User getTechnician() {
		return technician;
	}

	public void setTechnician(User technician) {
		this.technician = technician;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}