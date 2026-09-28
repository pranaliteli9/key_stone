package com.key_stone.DTO;

import java.time.LocalDateTime;

public class WorkOrderTimeLogResponseDTO {

	private Long id;
	private Long workOrderId;
	private Long technicianId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long durationMinutes;
	private String notes;

	public WorkOrderTimeLogResponseDTO() {
	}

	public WorkOrderTimeLogResponseDTO(Long id, Long workOrderId, Long technicianId, LocalDateTime startTime,
			LocalDateTime endTime, Long durationMinutes, String notes) {

		this.id = id;
		this.workOrderId = workOrderId;
		this.technicianId = technicianId;
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

	public Long getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}

	public Long getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(Long technicianId) {
		this.technicianId = technicianId;
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