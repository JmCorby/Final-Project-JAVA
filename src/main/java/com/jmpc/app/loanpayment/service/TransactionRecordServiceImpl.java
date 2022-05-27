package com.jmpc.app.loanpayment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jmpc.app.loanpayment.Repo.CustomerRepo;
import com.jmpc.app.loanpayment.Repo.LoanRepo;
import com.jmpc.app.loanpayment.Repo.TransactionRecordRepo;
import com.jmpc.app.loanpayment.models.TransactionRecord;

@Service
public class TransactionRecordServiceImpl {

	@Autowired
	private TransactionRecordRepo transactionRecordRepo;
	
	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private LoanRepo loanRepo;
	
	public List<TransactionRecord> getTransactionRecordByLoanId(long loanId) {
		return transactionRecordRepo.findByLoanId(loanId);
	}
	
	public TransactionRecord saveTransactionRecord (TransactionRecord transactionRecord) {
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
