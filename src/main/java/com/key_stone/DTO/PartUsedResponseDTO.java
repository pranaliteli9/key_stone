package com.key_stone.DTO;

public class PartUsedResponseDTO {

	private Long id;
	private Long workOrderId;
	private String partName;
	private Integer quantity;
	private String notes;

	public PartUsedResponseDTO() {
	}

	public PartUsedResponseDTO(Long id, Long workOrderId, String partName, Integer quantity, String notes) {

		this.id = id;
		this.workOrderId = workOrderId;
		this.partName = partName;
		this.quantity = quantity;
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