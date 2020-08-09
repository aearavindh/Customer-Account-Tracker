package ar20090702.foundation.bank.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ar20090702.foundation.bank.model.Account;
import ar20090702.foundation.bank.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AccountService accountService;
	
	String exampleAccountJson = "{}";
	
	@Test
	public void getAccountsTest() throws Exception{
		
        when(accountService.getAccounts()).thenReturn(new ArrayList<Account>());
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/account/get-all");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());

	}
	
	@Test
	public void createAccountTest() throws Exception{
		
        when(accountService.createAccount(any(Account.class))).thenReturn("Success");
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/account/create").content(exampleAccountJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(201, response.getStatus());
		
	}
	
	@Test
	public void editAccountTest() throws Exception{
		
        when(accountService.editAccount(any(Long.class),any(Double.class))).thenReturn("Success");
		
		RequestBuilder request = MockMvcRequestBuilders
				.put("/account/modify-balance").param("accountNumber","12345")
				.param("amount","3000");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());
		
	}
	
	@Test
	public void removeAccountTest() throws Exception{
		
        when(accountService.removeAccount(any(Long.class))).thenReturn("Success");
		
		RequestBuilder request = MockMvcRequestBuilders
				.delete("/account/remove").param("id","1");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());
		
	}
	
	@Test
	public void fundTransferTest() throws Exception{
		
        when(accountService.fundTransfer(any(Long.class), any(Long.class), any(Double.class))).thenReturn("Success");
		
		RequestBuilder request = MockMvcRequestBuilders
				.put("/account/fund-transfer").param("sourceAccountNumber","12345")
				.param("destinationAccountNumber","12346").param("amount","3000");
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus());
		
	}


}
