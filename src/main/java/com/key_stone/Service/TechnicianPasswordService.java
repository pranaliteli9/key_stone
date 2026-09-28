package com.key_stone.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.key_stone.DTO.ChangePasswordRequestDTO;
import com.key_stone.Entity.User;
import com.key_stone.Repository.UserRepository;

@Service
public class TechnicianPasswordService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	public TechnicianPasswordService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// Change logged-in technician password
	public void changePassword(String email, ChangePasswordRequestDTO dto) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Make sure user is a technician
		if (technician.getRole() == null || !"TECHNICIAN".equals(technician.getRole().name())) {

			throw new RuntimeException("User is not a technician");
		}

		// Check current password
		if (!passwordEncoder.matches(dto.getCurrentPassword(), technician.getPassword())) {

			throw new RuntimeException("Current password is incorrect");
		}

		// Check new password and confirm password
		if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {

			throw new RuntimeException("New password and confirm password do not match");
		}

		// Prevent using the same password
		if (passwordEncoder.matches(dto.getNewPassword(), technician.getPassword())) {

			throw new RuntimeException("New password must be different from current password");
		}

		// Encode new password
		String encodedPassword = passwordEncoder.encode(dto.getNewPassword());

		technician.setPassword(encodedPassword);

		// Save updated password
		userRepository.save(technician);
	}
}