package com.jmpc.app.loanpayment.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmpc.app.loanpayment.models.Customer;

public interface CustomerRepo extends JpaRepository <Customer, Long>{

}
