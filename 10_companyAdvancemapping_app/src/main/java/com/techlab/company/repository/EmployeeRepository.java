package com.techlab.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Page<Employee> findByStatus(EmploymentStatus status, Pageable pageable);

    Page<Employee> findByPosition(EmployeePosition position, Pageable pageable);
}
