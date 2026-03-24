package com.example.todo_mgmt.service;

import com.example.todo_mgmt.dto.LoginDTO;
import com.example.todo_mgmt.dto.RegisterDTO;

public interface AuthService {

	public String register(RegisterDTO registerDTO);

	public String login(LoginDTO loginDTO);
}
