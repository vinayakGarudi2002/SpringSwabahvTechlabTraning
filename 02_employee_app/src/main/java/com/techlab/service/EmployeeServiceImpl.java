package com.techlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.entity.Employee;
import com.techlab.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeerepo;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return   employeerepo.getAllEmployees();
	}

	@Override
	public void insertEmployee(Employee employess) {
		// TODO Auto-generated method stub
		 employeerepo.insertEmployee(employess);
	}
	
	

}
