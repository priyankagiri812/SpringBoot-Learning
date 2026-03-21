package com.example.banking_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_app.dto.TransactionDTO;
import com.example.banking_app.entity.Account;
import com.example.banking_app.entity.TransferFunds;
import com.example.banking_app.repository.AccountRepository;
import com.example.banking_app.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountRepository accountRepository;
	AccountService accountService;
	

	public AccountController(AccountService accountService, AccountRepository accountRepository) {
		super();
		this.accountService = accountService;
		this.accountRepository = accountRepository;
	}
	
	@Operation (
			summary = "Get the user account details by ID"
			)
	//REST API for Getting the account details by ID
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id){
		
		if(accountService.getAccountById(id)==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(accountService.getAccountById(id));
		}
	}

	@Operation (
			summary = "Create the Account in DB"
			)
	//REST API for Creation of Account
	@PostMapping
	public ResponseEntity<Account> addAccount(@RequestBody Account account){
		return ResponseEntity
			   .status(HttpStatus.CREATED)
			   .body(accountService.createAccount(account));
	}

	@Operation (
			summary = "Deposit the amount to account by ID"
			)
	@PutMapping("/{id}/deposit")
	public ResponseEntity<Account> depositAmount(@PathVariable Long id, 
												@RequestBody Map<String, Double> request){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(accountService.depositAmount(id, request.get("balance")));
		
	}

	@Operation (
			summary = "Withdraw the amount from account by ID"
			)
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Account> withdrawAmount(@PathVariable Long id, 
												@RequestBody Map<String, Double> request){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(accountService.withdrawAmount(id, request.get("balance")));
		
	}

	@Operation (
			summary = "Get the details of all the accounts"
			)
	@GetMapping("/all")
	public ResponseEntity<List<Account>> getAllAccounts(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(accountRepository.findAll());
	
	}

	@Operation (
			summary = "Delete the account by ID"
			)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id){
		accountRepository.deleteById(id);
		return ResponseEntity.ok("Account is deleted!");
	}

	@Operation (
			summary = "Transfer funds from one account to another"
			)
	//Build Transfer Funds REST API
	@PostMapping("/transfer")
	public ResponseEntity<String> transferFunds(@RequestBody TransferFunds tf){
		accountService.transferFunds(tf);
		return ResponseEntity.ok("Transfer Successfull!");
		
	}

	@Operation (
			summary = "View the transaction history by ID"
			)
	//Build Transactions REST API
	@GetMapping("/{accountId}/transactions")
	public ResponseEntity<List<TransactionDTO>> fetchAccountTransactions(@PathVariable Long accountId){
		
		List<TransactionDTO> transactions = accountService.getAccountTransactions(accountId);
		
		return ResponseEntity.ok(transactions);
		
	}
}
