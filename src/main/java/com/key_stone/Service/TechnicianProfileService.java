package com.key_stone.Service;

import org.springframework.stereotype.Service;

import com.key_stone.DTO.TechnicianProfileResponseDTO;
import com.key_stone.DTO.TechnicianProfileUpdateRequestDTO;
import com.key_stone.Entity.User;
import com.key_stone.Repository.UserRepository;

@Service
public class TechnicianProfileService {

	private final UserRepository userRepository;

	public TechnicianProfileService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	// Get logged-in technician profile
	public TechnicianProfileResponseDTO getProfile(String email) {

		// Find logged-in user
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Make sure logged-in user is a technician
		if (technician.getRole() == null || !"TECHNICIAN".equals(technician.getRole().name())) {

			throw new RuntimeException("User is not a technician");
		}

		// Convert Entity to DTO
		return new TechnicianProfileResponseDTO(technician.getId(), technician.getName(), technician.getEmail(),
				technician.getRole().name());
	}

	// Update logged-in technician profile
	public TechnicianProfileResponseDTO updateProfile(String email, TechnicianProfileUpdateRequestDTO dto) {

		// Find logged-in technician
		User technician = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		// Make sure user is a technician
		if (technician.getRole() == null || !"TECHNICIAN".equals(technician.getRole().name())) {

			throw new RuntimeException("User is not a technician");
		}

		// Update only name
		technician.setName(dto.getName());

		// Save updated technician
		User updatedTechnician = userRepository.save(technician);

		// Return updated profile
		return new TechnicianProfileResponseDTO(updatedTechnician.getId(), updatedTechnician.getName(),
				updatedTechnician.getEmail(), updatedTechnician.getRole().name());
	}
}