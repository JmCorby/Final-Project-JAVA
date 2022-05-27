package com.jmpc.app.loanpayment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmpc.app.loanpayment.Repo.CustomerRepo;
import com.jmpc.app.loanpayment.models.Customer;

@Service
public class CustomerServiceImpl {

	@Autowired
	private CustomerRepo customerRepo;

	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	public Customer getCustomerByName(Customer customer) {
		Customer first = customerRepo.findByFirstNameAndLastName(customer.firstName, customer.lastName).stream()
				.findFirst().orElse(null);
		if (first != null) {
			return first;
		} else {
			customerRepo.save(customer);
			return customer;
		}
	}
}
