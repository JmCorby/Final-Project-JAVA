package com.jmpc.app.loanpayment.models;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TransactionRecords")
public class TransactionRecord {

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;

	@ManyToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn(name = "loan_id", referencedColumnName = "id")
	private Loan loan;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	@Column(name = "amount_paid")
	public double amountPaid;

	@Column(name = "transaction_date")
	public LocalDateTime transactionDate;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public double getamountPaid() {
//		return amountPaid;
//	}
//
//	public void setAmountPaid(double amountPaid) {
//		this.amountPaid = amountPaid;
//	}
//
//	public String getTransactionDate() {
//		return transactionDate;
//	}
//
//	public void setTransactionDate(String transactionDate) {
//		this.transactionDate = transactionDate;
//	}

	public void setTransactionRecordValue(TransactionRecord transactionRecord) {
		this.amountPaid = transactionRecord.amountPaid;
		this.transactionDate = transactionRecord.transactionDate;
	}

}
