package com.key_stone.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.key_stone.DTO.LoginRequestDTO;
import com.key_stone.DTO.LoginResponseDTO;
import com.key_stone.Service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {

        LoginResponseDTO response = loginService.login(dto);

        return ResponseEntity.ok(response);
    }
}