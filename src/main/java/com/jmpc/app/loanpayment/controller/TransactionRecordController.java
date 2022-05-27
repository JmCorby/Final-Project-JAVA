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
import com.jmpc.app.loanpayment.models.TransactionRecord;

@RestController
@CrossOrigin
public class TransactionRecordController {
	
	@Autowired
	private TransactionRecordRepo transactionRecordRepo;
	
	private CustomerRepo customerRepo;

	@Autowired
	private LoanRepo loanRepo;

	@GetMapping(value = "/gettransactions/{loanId}")
	public List<TransactionRecord> getTransactionRecordByLoanId(@PathVariable("loanId") long loanId) {
		return transactionRecordRepo.findByLoanId(loanId);
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
}
