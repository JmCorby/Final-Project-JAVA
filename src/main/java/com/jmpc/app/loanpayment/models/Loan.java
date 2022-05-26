package com.jmpc.app.loanpayment.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Loans")
public class Loan {

	@ManyToOne
	@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
	public Customer customer;

	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<TransactionRecord> transactionRecords;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	@Column(name = "loan_amount")
	public double loanAmount;

	@Column(name = "balance")
	public double balance;

	@Column(name = "months_to_pay")
	public String monthsToPay;
	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMonthsToPay() {
		return monthsToPay;
	}

	public void setMonthsToPay(String monthsToPay) {
		this.monthsToPay = monthsToPay;
	}

	public void setLoanValue(Loan loan) {
		this.balance = loan.balance;
		this.loanAmount = loan.loanAmount;
		this.monthsToPay = loan.monthsToPay;
	}
}
