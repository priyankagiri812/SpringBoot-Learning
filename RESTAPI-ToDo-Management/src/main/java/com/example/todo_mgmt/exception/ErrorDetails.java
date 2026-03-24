package com.example.todo_mgmt.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ErrorDetails(LocalDateTime timestamp,
							String message,
							String details,
							HttpStatus erroCode) {

}
