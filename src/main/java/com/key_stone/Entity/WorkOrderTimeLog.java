package com.key_stone.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "work_order_time_logs")
public class WorkOrderTimeLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "work_order_id", nullable = false)
	private WorkOrder workOrder;

	@ManyToOne
	@JoinColumn(name = "technician_id", nullable = false)
	private User technician;

	@Column(name = "start_time", nullable = false)
	private LocalDateTime startTime;

	@Column(name = "end_time", nullable = false)
	private LocalDateTime endTime;

	@Column(name = "duration_minutes", nullable = false)
	private Long durationMinutes;

	@Column(columnDefinition = "TEXT")
	private String notes;

	public WorkOrderTimeLog() {
	}

	public WorkOrderTimeLog(Long id, WorkOrder workOrder, User technician, LocalDateTime startTime,
			LocalDateTime endTime, Long durationMinutes, String notes) {

		this.id = id;
		this.workOrder = workOrder;
		this.technician = technician;
		this.startTime = startTime;
		this.endTime = endTime;
		this.durationMinutes = durationMinutes;
		this.notes = notes;
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Long getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Long durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}