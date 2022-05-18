package com.jmpc.app.loanpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmpc.app.loanpayment.Repo.CustomerRepo;
import com.jmpc.app.loanpayment.Repo.LoanRepo;
import com.jmpc.app.loanpayment.Repo.TransactionRecordRepo;
import com.jmpc.app.loanpayment.models.Customer;
import com.jmpc.app.loanpayment.models.Loan;
import com.jmpc.app.loanpayment.models.TransactionRecord;

@RestController
public class ApiControllers {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private LoanRepo loanRepo;

	@Autowired
	private TransactionRecordRepo transactionRecordRepo;

	@GetMapping(value = "/")
	public String getPage() {
		return "Welcome";
	}

	// Customer methods
	@GetMapping(value = "/customers")
	public List<Customer> getCustomers() {
		return customerRepo.findAll();
	}

	@GetMapping(value = "/customers/{id}")
	public Customer getCustomer(@PathVariable("id") long id) {
		return customerRepo.findById(id).orElse(null);
	}

	@PostMapping(value = "/savecustomer")
	public String saveCustomer(@RequestBody Customer customer) {
		customerRepo.save(customer);
		return "Saved...";
	}
	
//	@GetMapping(value = "/confirmcustomer/{lastName}/{firstName}")
//	@ResponseBody
//	public List<Customer> getCustomer(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
//		return customerRepo.findAll();
//	}

	// Loan methods
	@GetMapping(value = "/allowloan/{salary}/{totalLoanAmount}")
	@ResponseBody
	public boolean getLoan(@PathVariable("salary") double salary, @PathVariable("totalLoanAmount") double loanAmount) {
		if (salary * 0.8 >= loanAmount) {
			return true;
		} else {
			return false;
		}
	}
	
	@GetMapping(value = "/loans/{id}")
	public Loan getloan(@PathVariable("id") long id) {
		return loanRepo.findById(id).orElse(null);
	}

	@GetMapping(value = "/loans")
	public List<Loan> getLoans() {
		return loanRepo.findAll();
	}

	@PutMapping(value = "/updateloan/{id}")
	public Loan updateLoan(@PathVariable("id") long id, @RequestBody Loan loan) {
		Loan loanToUpdate = loanRepo.findById(id).get();
		loanToUpdate.setLoanValue(loan);
		loanToUpdate = loanRepo.save(loanToUpdate);
		return loanToUpdate;
	}

	// Transaction Records methods
	@PostMapping(value = "/savetransaction")
	public String saveTransactionRecord(@RequestBody TransactionRecord transactionRecord) {
		transactionRecordRepo.save(transactionRecord);
		return "Saved...";
	}

}
