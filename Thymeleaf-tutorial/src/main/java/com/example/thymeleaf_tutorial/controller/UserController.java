package com.example.thymeleaf_tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.thymeleaf_tutorial.model.User;

@Controller
public class UserController {

	@GetMapping("/var-exp")
	public String variableExp(Model model) {
		
		User user = new User("Priyanka Giri", "giripriyanka812@gmail.com", "Engineer", "Female");
		
		model.addAttribute("user", user);
		
		return "var-exp";
	}
	
	@GetMapping("/selection-exp")
	public String selectionExp(Model model) {
		
		User user = new User("Priyanka Giri", "giripriyanka812@gmail.com", "Engineer", "Female");
		
		model.addAttribute("user", user);
		
		return "selection-exp";
	}
	
	@GetMapping("/message-exp")
	public String messageExp(Model model) {
		
		return "message-exp";
	}
	
}
