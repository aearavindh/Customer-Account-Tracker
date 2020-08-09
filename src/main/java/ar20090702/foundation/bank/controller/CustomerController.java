package ar20090702.foundation.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar20090702.foundation.bank.model.Customer;
import ar20090702.foundation.bank.service.CustomerService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/get-all")
	@ApiOperation("Get all the customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	@GetMapping("/get")
	@ApiOperation("Get a customer by id")
	public Customer getCustomer(@RequestParam long id){
		return customerService.getCustomer(id);
	}
	
	@PutMapping("/update")
	@ApiOperation("Update a particular customer details")
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
		String status = customerService.updateCustomer(customer);
		if(status.equals("Success"))
			return ResponseEntity.status(HttpStatus.OK).body("Customer details updated successfully...");
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error occured...");
	
	}

}
