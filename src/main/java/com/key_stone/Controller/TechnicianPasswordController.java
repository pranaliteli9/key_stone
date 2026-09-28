package com.key_stone.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.ChangePasswordRequestDTO;
import com.key_stone.Service.TechnicianPasswordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technician/password")
public class TechnicianPasswordController {

	private final TechnicianPasswordService passwordService;

	public TechnicianPasswordController(TechnicianPasswordService passwordService) {

		this.passwordService = passwordService;
	}

	// Change logged-in technician password
	@PutMapping("/change")
	public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequestDTO dto,
			Authentication authentication) {

		String email = authentication.getName();

		passwordService.changePassword(email, dto);

		return ResponseEntity.ok("Password changed successfully");
	}
}