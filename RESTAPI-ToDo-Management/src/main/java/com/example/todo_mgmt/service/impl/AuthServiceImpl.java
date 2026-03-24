package com.example.todo_mgmt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
import com.example.todo_mgmt.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepo;
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;

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

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

			// If authentication is success
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException e) {
			throw new RuntimeException("Invalid username or password");
		}

		return "Welcome " + loginDTO.getUsernameOrEmail();
	}

}
