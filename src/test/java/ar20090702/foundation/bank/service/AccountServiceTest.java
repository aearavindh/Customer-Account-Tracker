package ar20090702.foundation.bank.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ar20090702.foundation.bank.dao.AccountRepository;
import ar20090702.foundation.bank.model.Account;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	
	@InjectMocks
	private AccountServiceImplementation accountService;
	
	@Mock
	private AccountRepository accountRepository;
	
	List<Account> allAccounts = new ArrayList<Account>();
	Account account = new Account();
	
	@Test
	public void getAccountsTest(){
		when(accountRepository.findAll()).thenReturn(allAccounts);
		assertEquals(allAccounts, accountService.getAccounts());
	}
	
	@Test
	public void createAccountTest(){
		when(accountRepository.save(any(Account.class))).thenReturn(account);
		assertEquals("Success", accountService.createAccount(account));
	}
	
	@Test
	public void editAccountTest(){
		when(accountRepository.findByNumber(any(Long.class))).thenReturn(account);
		when(accountRepository.updateByNumber(any(Long.class),any(Double.class))).thenReturn(1);
		assertEquals("Success", accountService.editAccount(12345, 50000));
	}
	
	@Test
	public void removeAccountTest(){
		assertEquals("Success", accountService.removeAccount(1L));
	}
	
	@Test
	public void fundTransferTest(){
		when(accountRepository.findByNumber(any(Long.class))).thenReturn(account);
		assertEquals("Insufficient balance", accountService.fundTransfer(1L,2L,5000));
	}


}
