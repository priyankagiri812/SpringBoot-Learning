package com.example.thymeleaf_tutorial.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.thymeleaf_tutorial.model.UserForm;

@Controller
public class FormController {
	
	// Handle User Registration Page Request
	
	@GetMapping("/register")
	public String userRegistration(Model model) {
		
		UserForm userForm = new UserForm();
		
		model.addAttribute("userForm", userForm);
		
		List<String> listProfession = List.of("Developer","Tester","Architect","Manager");
		model.addAttribute("profession", listProfession);
				
		return "UserRegistrationForm";
	}
	@PostMapping("/register/save")
	public String submitForm(Model model,
							@ModelAttribute("userForm") UserForm userForm) {
		
		model.addAttribute("userForm", userForm);
		
		return "register-success";
		
	}

}
