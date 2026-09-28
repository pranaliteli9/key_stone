package com.key_stone.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parts_used")
public class PartUsed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "work_order_id", nullable = false)
	private WorkOrder workOrder;

	@Column(name = "part_name", nullable = false)
	private String partName;

	@Column(nullable = false)
	private Integer quantity;

	@Column(columnDefinition = "TEXT")
	private String notes;

	public PartUsed() {
	}

	public PartUsed(Long id, WorkOrder workOrder, String partName, Integer quantity, String notes) {

		this.id = id;
		this.workOrder = workOrder;
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

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
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