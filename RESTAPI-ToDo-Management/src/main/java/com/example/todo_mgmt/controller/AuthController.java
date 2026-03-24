package com.example.todo_mgmt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_mgmt.dto.LoginDTO;
import com.example.todo_mgmt.dto.RegisterDTO;
import com.example.todo_mgmt.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDTO dto) {
		authService.register(dto);

		return ResponseEntity.ok("User Registered Successfully.");

	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) {

		return ResponseEntity.ok(authService.login(dto));

	}

}
