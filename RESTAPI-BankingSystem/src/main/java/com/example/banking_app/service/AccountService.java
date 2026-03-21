package com.example.banking_app.service;

import java.util.List;

import com.example.banking_app.dto.TransactionDTO;
import com.example.banking_app.entity.Account;
import com.example.banking_app.entity.TransferFunds;

public interface AccountService {

	public Account createAccount(Account account);
	
	public Account getAccountById(Long id);

	public Account depositAmount(Long id, double balance);
	
	public Account withdrawAmount(Long id, double balance);
	
	public void deleteAccount(Long id);
	
	public void transferFunds(TransferFunds transferFund);
	
	public List<Account> getAllAccounts();
	
	public List<TransactionDTO> getAccountTransactions(Long accountId);
}
