package com.example.banking_app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.banking_app.dto.TransactionDTO;
import com.example.banking_app.entity.Account;
import com.example.banking_app.entity.Transaction;
import com.example.banking_app.entity.TransferFunds;
import com.example.banking_app.exception.AccountException;
import com.example.banking_app.repository.AccountRepository;
import com.example.banking_app.repository.TransactionRepository;
import com.example.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	AccountRepository accountRepository;
	TransactionRepository transactionRepository;

	public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		super();
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new AccountException("Account does not exists!"));
		return account;
	}

	@Override
	public Account depositAmount(Long id, double balance) {
		Account account = accountRepository
							.findById(id)
							.orElseThrow(() -> new AccountException("Account does not exists!"));
		
		//Calculate the total balance after deposit.
		double total = account.getBalance() + balance;
		account.setBalance(total);
		
		//Log the transaction
		Transaction t = new Transaction();
		t.setAccountId(id);
		t.setAccountHolderName(account.getAccountHolderName());
		t.setTimestamp(LocalDateTime.now());
		t.setTransactionType("Deposited");
		t.setBalance(balance);
		
		transactionRepository.save(t);
		
		//Save to DB.
		return accountRepository.save(account);
	}

	@Override
	public Account withdrawAmount(Long id, double balance) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account does not exists!"));
		
		if (account.getBalance() < balance) {
			throw new AccountException("Insufficient Balance!");
		}
		
		//Calculate the total balance after withdrawal.
		double total = account.getBalance() - balance;
		account.setBalance(total);
		
		//Log the transaction
		Transaction t = new Transaction();
		t.setAccountId(id);
		t.setAccountHolderName(account.getAccountHolderName());
		t.setTimestamp(LocalDateTime.now());
		t.setTransactionType("Withdrawn");
		t.setBalance(balance);
		
		transactionRepository.save(t);
		
		//Save to DB.
		return accountRepository.save(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		
		return accountRepository.findAll();
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
						.findById(id)
						.orElseThrow(() -> new AccountException("Account does not exists!"));
		
		accountRepository.deleteById(id);
				
	}

	@Override
	public void transferFunds(TransferFunds transferFund) {
		
		//Get the ID from FROM account
		Account fromAccount = accountRepository.findById(transferFund.fromAccountId())
									.orElseThrow(() -> new AccountException("Account does not exists!"));
		
		//Get the ID from TO account
		Account toAccount = accountRepository.findById(transferFund.toAccountId())
											.orElseThrow(() -> new AccountException("Account does not exists!"));
		
		//Debit the amount from account
		fromAccount.setBalance(fromAccount.getBalance() - transferFund.balance());
		
		//Deposit amount to Receiver's account
		toAccount.setBalance(toAccount.getBalance() + transferFund.balance());
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		//Log the transaction
		Transaction t = new Transaction();
		t.setAccountId(transferFund.fromAccountId());
		t.setTimestamp(LocalDateTime.now());
		t.setTransactionType("Transferred");
		t.setBalance(transferFund.balance());
		
		transactionRepository.save(t);
		
	}

	@Override
	public List<TransactionDTO> getAccountTransactions(Long accountId) {
		//Fetching data from DB (Entity)s
		List<Transaction> transactions = transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);
		
		return transactions.stream() //loop through each transaction
		.map((t) -> convertEntitytoDTO(t)) //transform each Transaction into TransactionDTO
		.collect(Collectors.toList()); //convert back to a lists
		
	}
	
	//Convert Entity to DTO.
	private TransactionDTO convertEntitytoDTO(Transaction t) {
		
		return new TransactionDTO(
				t.getId(),
				t.getAccountId(),
				t.getBalance(),
				t.getTransactionType(),
				t.getTimestamp());
		
	}
	
	


	
	

}
