package com.key_stone.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PartUsedRequestDTO {

	@NotBlank(message = "Part name is required")
	private String partName;

	@NotNull(message = "Quantity is required")
	@Min(value = 1, message = "Quantity must be at least 1")
	private Integer quantity;

	private String notes;

	public PartUsedRequestDTO() {
	}

	public PartUsedRequestDTO(String partName, Integer quantity, String notes) {

		this.partName = partName;
		this.quantity = quantity;
		this.notes = notes;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}