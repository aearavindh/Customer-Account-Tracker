package ar20090702.foundation.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar20090702.foundation.bank.model.Account;
import ar20090702.foundation.bank.service.AccountService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/get-all")
	@ApiOperation("Get all accounts")
	public List<Account> getAccounts(){
		return accountService.getAccounts();
	}
	
	@PostMapping("/create")
	@ApiOperation("Create an account")
	public ResponseEntity<String> createAccount(@RequestBody Account account){
		String status = accountService.createAccount(account);
		if(status.equals("Success"))
			return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully...");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured...");
	}
	
	@PutMapping("/modify-balance")
	@ApiOperation("Modify the account balance")
	public ResponseEntity<String> editAccount(@RequestParam long accountNumber, @RequestParam double amount){
		String status = accountService.editAccount(accountNumber, amount);
		if(status.equals("Success"))
			return ResponseEntity.status(HttpStatus.OK).body("Account details modified successfully...");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured...");
	}
	
	@DeleteMapping("/remove")
	@ApiOperation("Remove an account")
	public ResponseEntity<String> removeAccount(@RequestParam long id){
		String status = accountService.removeAccount(id);
		if(status.equals("Success"))
			return ResponseEntity.status(HttpStatus.OK).body("Account removed successfully...");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured...");
	}
	
	@PutMapping("/fund-transfer")
	@ApiOperation("Transfer fund from one account to another")
	public ResponseEntity<String> fundTransfer(@RequestParam long sourceAccountNumber, @RequestParam long destinationAccountNumber, @RequestParam double amount){
		String status = accountService.fundTransfer(sourceAccountNumber, destinationAccountNumber, amount);
		if(status.equals("Success"))
			return ResponseEntity.status(HttpStatus.OK).body("Fund transferred successfully...");
		else if(status.equals("Insufficient balance"))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Insufficient balance in source account...");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured...");
	}
	
}
