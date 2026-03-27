package com.example.todo_mgmt;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.todo_mgmt.controller.ToDoController;

@SpringBootApplication
public class RestapiToDoManagementApplication {

	private static final Logger logger = LoggerFactory.getLogger(RestapiToDoManagementApplication.class);
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		
		logger.info("Starting RestapiToDoManagementApplication");
		
		SpringApplication.run(RestapiToDoManagementApplication.class, args);
	}

}
