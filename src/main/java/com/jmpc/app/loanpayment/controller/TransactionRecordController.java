package com.jmpc.app.loanpayment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jmpc.app.loanpayment.models.TransactionRecord;
import com.jmpc.app.loanpayment.service.TransactionRecordServiceImpl;

@RestController
@CrossOrigin
public class TransactionRecordController {

	@Autowired
	private TransactionRecordServiceImpl transactionRecordService;

	@GetMapping(value = "/gettransactions/{loanId}")
	public List<TransactionRecord> getTransactionRecordByLoanId(@PathVariable("loanId") long loanId) {
		return transactionRecordService.getTransactionRecordByLoanId(loanId);
	}

	@PostMapping(value = "/savetransaction")
	public TransactionRecord saveTransactionRecord(@RequestBody TransactionRecord transactionRecord) {
		return transactionRecordService.saveTransactionRecord(transactionRecord);
	}
}
