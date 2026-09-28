package com.key_stone.DTO;

import java.time.LocalDateTime;

public class WorkOrderNoteResponseDTO {

	private Long id;
	private Long workOrderId;
	private Long technicianId;
	private String technicianName;
	private String note;
	private LocalDateTime createdAt;

	public WorkOrderNoteResponseDTO() {
	}

	public WorkOrderNoteResponseDTO(Long id, Long workOrderId, Long technicianId, String technicianName, String note,
			LocalDateTime createdAt) {

		this.id = id;
		this.workOrderId = workOrderId;
		this.technicianId = technicianId;
		this.technicianName = technicianName;
		this.note = note;
		this.createdAt = createdAt;
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

	public String getTechnicianName() {
		return technicianName;
	}

	public void setTechnicianName(String technicianName) {
		this.technicianName = technicianName;
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