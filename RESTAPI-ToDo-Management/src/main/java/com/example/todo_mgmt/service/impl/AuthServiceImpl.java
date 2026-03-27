package com.example.todo_mgmt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo_mgmt.dto.LoginDTO;
import com.example.todo_mgmt.dto.RegisterDTO;
import com.example.todo_mgmt.entity.User;
import com.example.todo_mgmt.exception.ToDoAPIException;
import com.example.todo_mgmt.repository.UserRepository;
import com.example.todo_mgmt.security.JwtTokenProvider;
import com.example.todo_mgmt.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	private UserRepository userRepo;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider tokenProvider;

	@Override
	public String register(RegisterDTO registerDTO) {

		if (userRepo.existsByUsername(registerDTO.getUsername())) {
			throw new ToDoAPIException("Username already exists in DB.");
		}

		if (userRepo.existsByemail(registerDTO.getEmail())) {
			throw new ToDoAPIException("Email already exists in DB.");
		}

		User user = new User();

		user.setName(registerDTO.getName());
		user.setUsername(registerDTO.getUsername());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

		userRepo.save(user);

		return "User Registeration Succesfull!";
	}

	@Override
	public String login(LoginDTO loginDTO) {

		Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

		// If authentication is success
		logger.info("User authenticated successfully: {}", loginDTO.getUsernameOrEmail());
		SecurityContextHolder.getContext().setAuthentication(authentication);
			
		String token = tokenProvider.generateToken(authentication);
		
		logger.info("JWT token generated for user: {}", loginDTO.getUsernameOrEmail());
		
		return token;
	}

}
