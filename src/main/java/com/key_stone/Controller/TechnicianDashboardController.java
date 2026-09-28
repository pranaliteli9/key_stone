package com.key_stone.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.key_stone.DTO.TechnicianDashboardResponseDTO;
import com.key_stone.Service.TechnicianDashboardService;

@RestController
@RequestMapping("/api/technician/dashboard")
public class TechnicianDashboardController {

	private final TechnicianDashboardService dashboardService;

	public TechnicianDashboardController(TechnicianDashboardService dashboardService) {

		this.dashboardService = dashboardService;
	}

	// Get technician dashboard summary
	@GetMapping
	public ResponseEntity<TechnicianDashboardResponseDTO> getDashboard(Authentication authentication) {

		String email = authentication.getName();

		TechnicianDashboardResponseDTO response = dashboardService.getDashboard(email);

		return ResponseEntity.ok(response);
	}
}