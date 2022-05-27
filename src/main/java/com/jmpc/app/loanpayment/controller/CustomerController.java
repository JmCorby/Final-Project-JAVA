package com.jmpc.app.loanpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmpc.app.loanpayment.Repo.CustomerRepo;
import com.jmpc.app.loanpayment.models.Customer;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping(value = "/customers")
	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	@PostMapping(value = "/customers/getbyname")
	public Customer getCustomerByName(@RequestBody Customer customer) {
		
	Customer first = customerRepo.findByFirstNameAndLastName(customer.firstName, customer.lastName).stream().findFirst().orElse(null);
		
		if(first != null) {
			return first;
		} else {
			customerRepo.save(customer);
			return customer;
		}
	}

}
