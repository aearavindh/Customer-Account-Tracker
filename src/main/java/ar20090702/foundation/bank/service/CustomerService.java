package ar20090702.foundation.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar20090702.foundation.bank.dao.CustomerRepository;
import ar20090702.foundation.bank.model.Customer;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomer(long id) {
		Optional<Customer> c = customerRepository.findById(id);
		return c.get();
	}

	public String updateCustomer(Customer customer) {
		int count = customerRepository.update(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAddress());
		return count==1? "Success": "Failure";
	}

}
