package com.key_stone.DTO;

import jakarta.validation.constraints.NotBlank;

public class WorkOrderNoteRequestDTO {

	@NotBlank(message = "Note is required")
	private String note;

	public WorkOrderNoteRequestDTO() {
	}

	public WorkOrderNoteRequestDTO(String note) {
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}