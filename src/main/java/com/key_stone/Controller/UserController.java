package com.key_stone.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.UserRegistrationDTO;
import com.key_stone.DTO.UserResponseDTO;
import com.key_stone.Entity.User;
import com.key_stone.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {

		User user = new User();

		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());

		User savedUser = userService.registerUser(user);

		UserResponseDTO response = new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(),
				savedUser.getRole());

		return ResponseEntity.ok(response);
	}
}