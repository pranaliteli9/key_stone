package com.key_stone.DTO;

import com.key_stone.Enum.WorkOrderStatus;

public class WorkOrderResponseDTO {

	private Long id;
	private String title;
	private String description;
	private String location;
	private WorkOrderStatus status;
	private Long technicianId;
	private String technicianName;
	private String technicianEmail;
	private String technicianRole;

	public WorkOrderResponseDTO() {
	}

	public WorkOrderResponseDTO(Long id, String title, String description, String location, WorkOrderStatus status,
			Long technicianId, String technicianName, String technicianEmail, String technicianRole) {

		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.status = status;
		this.technicianId = technicianId;
		this.technicianName = technicianName;
		this.technicianEmail = technicianEmail;
		this.technicianRole = technicianRole;
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

	public String getTechnicianEmail() {
		return technicianEmail;
	}

	public void setTechnicianEmail(String technicianEmail) {
		this.technicianEmail = technicianEmail;
	}

	public String getTechnicianRole() {
		return technicianRole;
	}

	public void setTechnicianRole(String technicianRole) {
		this.technicianRole = technicianRole;
	}
}