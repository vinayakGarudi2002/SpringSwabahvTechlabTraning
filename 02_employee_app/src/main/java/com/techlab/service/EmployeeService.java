package com.techlab.service;

import java.util.List;

import com.techlab.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	
	 void insertEmployee(Employee employess);
}
