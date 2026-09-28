package com.key_stone.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class WorkOrderTimeLogRequestDTO {

	@NotNull(message = "Start time is required")
	private LocalDateTime startTime;

	@NotNull(message = "End time is required")
	private LocalDateTime endTime;

	private String notes;

	public WorkOrderTimeLogRequestDTO() {
	}

	public WorkOrderTimeLogRequestDTO(LocalDateTime startTime, LocalDateTime endTime, String notes) {

		this.startTime = startTime;
		this.endTime = endTime;
		this.notes = notes;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}