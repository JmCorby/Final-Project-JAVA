package com.jmpc.app.loanpayment.controller;

import java.time.LocalDateTime;
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
		var transactions = transactionRecordRepo.findByLoanId(loan.id);
		double totalAmountPaid = 0;
		for (var transaction : transactions) {
			totalAmountPaid += transaction.amountPaid;
		}
		double balance = loan.loanAmount - totalAmountPaid - transactionRecord.amountPaid;
		loan.balance = balance;
		loanRepo.save(loan);
		return transactionRecordRepo.save(transactionRecord);
	}
	
	@GetMapping(value = "/gettransactions/{loanId}")
	public List<TransactionRecord> getTransactionRecordByLoanId(@PathVariable("loanId") long loanId) {
		return transactionRecordRepo.findByLoanId(loanId);
	}

}
