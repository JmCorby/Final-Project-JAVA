package com.jmpc.app.loanpayment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmpc.app.loanpayment.models.Loan;

public interface LoanRepo extends JpaRepository <Loan, Long>{

}
