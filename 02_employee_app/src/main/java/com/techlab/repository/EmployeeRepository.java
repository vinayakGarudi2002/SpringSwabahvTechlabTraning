package com.techlab.repository;

import java.util.List;

import com.techlab.entity.Employee;

public interface EmployeeRepository {
	
	List<Employee> getAllEmployees();
	
	 void insertEmployee(Employee employess);

}
