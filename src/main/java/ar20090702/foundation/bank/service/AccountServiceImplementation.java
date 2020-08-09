package ar20090702.foundation.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar20090702.foundation.bank.dao.AccountRepository;
import ar20090702.foundation.bank.model.Account;

@Service
public class AccountServiceImplementation implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<Account> getAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public String createAccount(Account account) {
		Account a = accountRepository.save(account);
		return a==null? "Failure":"Success";
	}

	@Override
	public String editAccount(long accountNumber, double amount) {
		Account a = accountRepository.findByNumber(accountNumber);
		double newBalance = a.getBalance()+amount;
		int count = accountRepository.updateByNumber(accountNumber, newBalance);
		return count==1? "Success":"Failure";
	}

	@Override
	public String removeAccount(Long id) {
		accountRepository.deleteById(id);
		return "Success";
	}
	
	@Override
	public String fundTransfer(long sourceAccountNumber, long destinationAccountNumber, double amount) {
		Account source = accountRepository.findByNumber(sourceAccountNumber);
		Account destination = accountRepository.findByNumber(destinationAccountNumber);
		if(source.getBalance() < amount)
			return "Insufficient balance";
		else {
			accountRepository.updateByNumber(sourceAccountNumber, source.getBalance()-amount);
			accountRepository.updateByNumber(destinationAccountNumber, destination.getBalance()+amount);
			return "Success";
		}
	}

}
