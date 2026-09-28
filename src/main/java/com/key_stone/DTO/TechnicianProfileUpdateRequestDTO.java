package com.key_stone.DTO;

import jakarta.validation.constraints.NotBlank;

public class TechnicianProfileUpdateRequestDTO {

	@NotBlank(message = "Name is required")
	private String name;

	public TechnicianProfileUpdateRequestDTO() {
	}

	public TechnicianProfileUpdateRequestDTO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}