package com.jmpc.app.loanpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmpc.app.loanpayment.models.Customer;
import com.jmpc.app.loanpayment.service.CustomerServiceImpl;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@GetMapping(value = "/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@PostMapping(value = "/customers/getbyname")
	public Customer getCustomerByName(@RequestBody Customer customer) {
		return customerService.getCustomerByName(customer);
	}

}
