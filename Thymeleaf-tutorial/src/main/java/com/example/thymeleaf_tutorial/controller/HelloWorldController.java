package com.example.thymeleaf_tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {
	
	// Handler method to handle Hello World request
	//http://localhost:8080/hello-world
	@GetMapping("/hello-world")
	public String helloWorld(Model model) {
		model.addAttribute("message", "Hello World!");
		return "hello-world";
	}

}
