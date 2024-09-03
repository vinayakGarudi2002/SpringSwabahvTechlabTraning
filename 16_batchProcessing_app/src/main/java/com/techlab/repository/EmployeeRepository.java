package com.techlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}
