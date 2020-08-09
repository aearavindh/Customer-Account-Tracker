package ar20090702.foundation.bank.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import ar20090702.foundation.bank.model.Account;
import ar20090702.foundation.bank.model.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	
	@Autowired
	AccountRepository repository;
	
	Account a = new Account(1L,"a",2000,new Customer());
	
	@Before
	public void saveMockData() {
		repository.save(a);
	}
	
	@Test
	public void saveTest() {
		Account account = repository.save(a);
		assertEquals(a, account);
	}
	
	@Test 
	public void findByNumberTest() {
		Account account = repository.findByNumber(1L);
		assertEquals(a, account);
	}
	
	@Test
	public void updateByNumberTest() {
		int count = repository.updateByNumber(1L, 5000);
		assertEquals(1, count);
	}

}
