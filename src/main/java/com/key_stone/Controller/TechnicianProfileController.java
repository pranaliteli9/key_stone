package com.key_stone.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.key_stone.DTO.TechnicianProfileResponseDTO;
import com.key_stone.DTO.TechnicianProfileUpdateRequestDTO;
import com.key_stone.Service.TechnicianProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/technician/profile")
public class TechnicianProfileController {

	private final TechnicianProfileService profileService;

	public TechnicianProfileController(TechnicianProfileService profileService) {

		this.profileService = profileService;
	}

	// Get logged-in technician profile
	@GetMapping
	public ResponseEntity<TechnicianProfileResponseDTO> getProfile(Authentication authentication) {

		String email = authentication.getName();

		TechnicianProfileResponseDTO response = profileService.getProfile(email);

		return ResponseEntity.ok(response);
	}

	// Update logged-in technician profile
	@PutMapping
	public ResponseEntity<TechnicianProfileResponseDTO> updateProfile(
			@Valid @RequestBody TechnicianProfileUpdateRequestDTO dto, Authentication authentication) {

		String email = authentication.getName();

		TechnicianProfileResponseDTO response = profileService.updateProfile(email, dto);

		return ResponseEntity.ok(response);
	}
}