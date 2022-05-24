package com.jmpc.app.loanpayment.Repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jmpc.app.loanpayment.models.TransactionRecord;

public interface TransactionRecordRepo extends JpaRepository <TransactionRecord, Long> {
	@Query("SELECT * FROM transaction_records WHERE loan_id = ?1")
	List<TransactionRecord> findByLoanId(long loanId);
}
