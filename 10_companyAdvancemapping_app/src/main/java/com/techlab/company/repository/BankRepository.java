package com.techlab.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>{

}
