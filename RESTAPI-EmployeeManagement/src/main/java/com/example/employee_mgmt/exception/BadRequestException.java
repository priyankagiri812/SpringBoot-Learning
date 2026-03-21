package com.example.employee_mgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_GATEWAY)
public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
