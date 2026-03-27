package com.example.todo_mgmt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo_mgmt.dto.JwtAuthResponse;
import com.example.todo_mgmt.dto.LoginDTO;
import com.example.todo_mgmt.dto.RegisterDTO;
import com.example.todo_mgmt.service.AuthService;
import com.example.todo_mgmt.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
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

	/*
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody
	 * LoginDTO dto) {
	 * 
	 * return ResponseEntity.ok(authService.login(dto));
	 * 
	 * }
	 */
	
	 @PostMapping("/login") 
	 public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO dto) {
		
		 logger.info("Login request received for user: {}", dto.getUsernameOrEmail());
		 
		 String token = authService.login(dto);
		 	
		 JwtAuthResponse authResponse = new JwtAuthResponse();
		 
		 authResponse.setAccessToken(token);
			 
		 return new ResponseEntity<>(authResponse,HttpStatus.OK);
			 
	}

}
