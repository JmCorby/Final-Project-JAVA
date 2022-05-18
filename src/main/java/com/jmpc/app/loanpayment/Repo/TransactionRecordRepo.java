package com.jmpc.app.loanpayment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmpc.app.loanpayment.models.TransactionRecord;

public interface TransactionRecordRepo extends JpaRepository <TransactionRecord, Long> {

}
