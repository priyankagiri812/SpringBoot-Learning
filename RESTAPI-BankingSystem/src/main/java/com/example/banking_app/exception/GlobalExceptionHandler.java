package com.example.banking_app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Handle specific exception - AccountException
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(AccountException ex, WebRequest wb){
		
		ErrorDetails errorDetails = new ErrorDetails
				(LocalDateTime.now(), 
				 ex.getMessage(), 
				wb.getDescription(false), 
				HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	//Handle Generic 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception ex, WebRequest wb){
		
		ErrorDetails errorDetails = new ErrorDetails
				(LocalDateTime.now(), 
				 ex.getMessage(), 
				wb.getDescription(false), 
				HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
