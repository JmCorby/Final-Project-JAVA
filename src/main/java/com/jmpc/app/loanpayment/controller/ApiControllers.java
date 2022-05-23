package com.jmpc.app.loanpayment.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@CrossOrigin
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
		try {
			var result = loanRepo.findAll();
			return result; 
		}
		catch (Exception e) {
			return null;
		}
	}
	
//new
//	@PostMapping(value = "/saveloan")
//	public Loan saveLoan(@RequestBody Loan loan, @PathVariable("id") long id) {
//		Loan loanToSave = loanRepo.findById(id).get();
//		loanToSave.setLoanValue(loan);
//		loanToSave = loanRepo.save(loanToSave);
//		return loanToSave;
//	}
	
	@PostMapping(value = "saveloan")
	public Loan saveLoan(@RequestBody Loan loan) {
		var customer = customerRepo.findById(loan.customer.id).orElse(null);
		loan.customer = customer;
		return loanRepo.save(loan);
	}
	
	
//	@PutMapping(value = "/updateloan/{id}")
//	public Loan updateLoan(@PathVariable("id") long id, @RequestBody Loan loan) {
//		Loan loanToUpdate = loanRepo.findById(id).get();
//		loanToUpdate.setLoanValue(loan);
//		loanToUpdate = loanRepo.save(loanToUpdate);
//		return loanToUpdate;
//	}

	// Transaction Records methods
	@GetMapping(value = "/gettransactions")
	public List<TransactionRecord> getTransactionRecord() {
		return transactionRecordRepo.findAll();
	}

	
	@PostMapping(value = "/savetransaction")
	public TransactionRecord transactionRecord (@RequestBody TransactionRecord transactionRecord) {
		var customer = customerRepo.findById(transactionRecord.getCustomer().id).orElse(null);
		var loan = loanRepo.findById(transactionRecord.getLoan().id).orElse(null);
		transactionRecord.setCustomer(customer);
		transactionRecord.setLoan(loan);
		transactionRecord.transactionDate = LocalDateTime.now();
		return transactionRecordRepo.save(transactionRecord);
	}
	

}
