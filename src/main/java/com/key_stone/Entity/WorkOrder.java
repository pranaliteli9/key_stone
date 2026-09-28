package com.key_stone.Entity;

import com.key_stone.Enum.WorkOrderStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "work_orders")
public class WorkOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private String location;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WorkOrderStatus status;

	@ManyToOne
	@JoinColumn(name = "technician_id")
	private User technician;

	public WorkOrder() {
	}

	public WorkOrder(Long id, String title, String description, String location, WorkOrderStatus status,
			User technician) {

		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.status = status;
		this.technician = technician;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public WorkOrderStatus getStatus() {
		return status;
	}

	public void setStatus(WorkOrderStatus status) {
		this.status = status;
	}

	public User getTechnician() {
		return technician;
	}

	public void setTechnician(User technician) {
		this.technician = technician;
	}
}