package com.techlab.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.SalaryAccount;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Integer>{

}
