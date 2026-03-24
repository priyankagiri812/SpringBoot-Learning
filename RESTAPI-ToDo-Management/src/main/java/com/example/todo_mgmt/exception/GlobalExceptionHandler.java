package com.example.todo_mgmt.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Handle specific exception
	@ExceptionHandler(ToDoAPIException.class)
	public ResponseEntity<ErrorDetails> handleToDoAPIException(ToDoAPIException ex, WebRequest wb) {

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), wb.getDescription(false),
				HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
