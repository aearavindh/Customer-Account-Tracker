package ar20090702.foundation.bank.service;

import java.util.List;


import ar20090702.foundation.bank.model.Account;


public interface AccountService {

	List<Account> getAccounts();

	String createAccount(Account account);

	String editAccount(long accountNumber, double amount);

	String removeAccount(Long id);

	String fundTransfer(long sourceAccountNumber, long destinationAccountNumber, double amount);

}
