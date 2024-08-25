package com.techlab.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.SalaryTransaction;

public interface SalaryTransactionRepository extends JpaRepository<SalaryTransaction, Integer>{

}
