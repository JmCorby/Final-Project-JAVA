package com.jmpc.app.loanpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmpc.app.loanpayment.Repo.CustomerRepo;
import com.jmpc.app.loanpayment.Repo.LoanRepo;
import com.jmpc.app.loanpayment.models.Loan;

@RestController
@CrossOrigin
public class LoanController {
	
	@Autowired 
	private LoanRepo loanRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping(value = "/loans/{id}")
	public Loan getloan(@PathVariable("id") long id) {
		return loanRepo.findById(id).orElse(null);
	}

	@GetMapping(value = "/loans")
	public List<Loan> getLoans() {
		try {
			var result = loanRepo.findAll();
			return result; 
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping(value = "saveloan")
	public Loan saveLoan(@RequestBody Loan loan) {
		var customer = customerRepo.findById(loan.customer.id).orElse(null);
		loan.customer = customer;
		return loanRepo.save(loan);
	}
}
