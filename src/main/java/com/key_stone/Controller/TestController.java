package com.key_stone.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	// Public API
	@GetMapping("/api/test")
	public String test() {
		return "KEY_STONE Backend is Running";
	}

	// Any authenticated user
	@GetMapping("/api/protected")
	public String protectedApi() {
		return "JWT Authentication is working";
	}

	// TECHNICIAN role only
	@GetMapping("/api/technician/test")
	public String technicianTest() {
		return "Technician API is accessible";
	}
}