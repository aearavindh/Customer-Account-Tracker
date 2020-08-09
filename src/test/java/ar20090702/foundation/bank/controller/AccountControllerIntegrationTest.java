package ar20090702.foundation.bank.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ar20090702.foundation.bank.model.Account;

@RunWith(SpringRunner.class)
public class AccountControllerIntegrationTest {
	
	String url = "http://localhost:9000/account";
	
	Account account = new Account();
	
	@Test
	public void getAccountsTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<Account[]> response = testRestTemplate.
		  getForEntity(url + "/get-all", Account[].class);
		 
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	@Test
	public void createAccountTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = testRestTemplate.
		  postForEntity(url + "/create",account, String.class);
		 
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	}

}
