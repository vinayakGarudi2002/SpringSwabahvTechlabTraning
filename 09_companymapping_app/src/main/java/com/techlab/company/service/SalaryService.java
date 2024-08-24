package com.techlab.company.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlab.company.entity.Salary;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.entity.SalaryTransaction;

public interface SalaryService {
	
	List<Salary> getAllSalary();
	
	Salary addUpdateSalary(Salary salary);
	
	String deletSalary(int salaryId);
	
	
	SalaryTransaction getSalaryTransaction(int salaryId);
	
	
	SalaryTransaction updateSalaryTransaction(int salaryId,SalaryTransaction transaction);
	
	
	
	

}
