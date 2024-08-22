package com.techlab.company.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findByDepartmentName(String departmentName);
}

