package com.example.banking_app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public record ErrorDetails(LocalDateTime timestamp,
							String message,
							String details,
							HttpStatus erroCode) {

}
