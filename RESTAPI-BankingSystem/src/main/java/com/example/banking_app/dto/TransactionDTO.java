package com.example.banking_app.dto;

import java.time.LocalDateTime;

public record TransactionDTO(Long id,
							 Long accountId,
							 double balance,
							 String transactionType,
							 LocalDateTime timestamp){

}
