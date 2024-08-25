package com.techlab.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

}
