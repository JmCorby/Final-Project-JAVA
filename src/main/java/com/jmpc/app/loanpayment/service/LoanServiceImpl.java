package com.jmpc.app.loanpayment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmpc.app.loanpayment.Repo.CustomerRepo;
import com.jmpc.app.loanpayment.Repo.LoanRepo;
import com.jmpc.app.loanpayment.models.Loan;

@Service
public class LoanServiceImpl {

	@Autowired
	private LoanRepo loanRepo;

	@Autowired
	private CustomerRepo customerRepo;

	public Loan getloan(long id) {
		return loanRepo.findById(id).orElse(null);
	}

	public List<Loan> getLoans() {
		try {
			var result = loanRepo.findAll();
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public Loan saveLoan(Loan loan) {
		var customer = customerRepo.findById(loan.customer.id).orElse(null);
		loan.customer = customer;
		return loanRepo.save(loan);
	}
}
