package com.example.banking_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot REST API - Banking Application",
				description = "Spring Boot REST API - Banking Application",
				version = "v1.0"
				)
		)
public class RestapiBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiBankingSystemApplication.class, args);
	}

}
