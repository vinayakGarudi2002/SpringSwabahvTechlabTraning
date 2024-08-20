package com.techlab.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
public class EmployeeRepositoryImpl implements EmployeeRepository {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Employee> getAllEmployees() {
		
		// TODO Auto-generated method stub
		TypedQuery<Employee> query=manager.createQuery("select s  from Employee s",Employee.class);
		return query.getResultList();
	}

	

	@Override
	@Transactional
	public void insertEmployee(Employee employee) {
		
			manager.persist(employee);
		
	}
	
	

}
