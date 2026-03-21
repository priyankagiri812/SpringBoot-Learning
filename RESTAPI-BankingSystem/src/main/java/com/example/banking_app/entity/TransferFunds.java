package com.example.banking_app.entity;

public record TransferFunds(Long fromAccountId,
							Long toAccountId,
							double balance) {

}
