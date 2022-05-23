package com.jmpc.app.loanpayment.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmpc.app.loanpayment.models.Customer;

public interface CustomerRepo extends JpaRepository <Customer, Long>{

	List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
		
}
