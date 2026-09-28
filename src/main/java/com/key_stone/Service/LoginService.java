package com.key_stone.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.key_stone.DTO.LoginRequestDTO;
import com.key_stone.DTO.LoginResponseDTO;
import com.key_stone.Entity.User;
import com.key_stone.Repository.UserRepository;
import com.key_stone.Security.JwtService;

@Service
public class LoginService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtService jwtService;

	public LoginService(AuthenticationManager authenticationManager, UserRepository userRepository,
			JwtService jwtService) {

		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.jwtService = jwtService;
	}

	public LoginResponseDTO login(LoginRequestDTO dto) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

		User user = userRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		String token = jwtService.generateToken(user.getEmail(), user.getRole().name());

		return new LoginResponseDTO(token, user.getId(), user.getName(), user.getEmail(), user.getRole().name());
	}
}