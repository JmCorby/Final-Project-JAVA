package com.jmpc.app.loanpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmpc.app.loanpayment.models.Loan;
import com.jmpc.app.loanpayment.service.LoanServiceImpl;

@RestController
@CrossOrigin
public class LoanController {

	@Autowired
	private LoanServiceImpl loanService;

	@GetMapping(value = "/loans/{id}")
	public Loan getloan(@PathVariable("id") long id) {
		return loanService.getloan(id);
	}

	@GetMapping(value = "/loans")
	public List<Loan> getLoans() {
		return loanService.getLoans();
	}

	@PostMapping(value = "saveloan")
	public Loan saveLoan(@RequestBody Loan loan) {
		return loanService.saveLoan(loan);
	}
}
