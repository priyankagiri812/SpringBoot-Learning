package com.example.banking_app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction_history")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
	private Long id;
	private Long accountId;
	private double balance;
	private String transactionType;
	private LocalDateTime timestamp;
	private String accountHolderName;

}
