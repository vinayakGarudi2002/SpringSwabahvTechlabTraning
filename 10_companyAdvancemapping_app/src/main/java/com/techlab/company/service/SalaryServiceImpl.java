package com.techlab.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.company.entity.Employee;
import com.techlab.company.entity.Salary;
import com.techlab.company.entity.SalaryTransaction;
import com.techlab.company.repository.EmployeeRepository;
import com.techlab.company.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {
	
	@Autowired
	private SalaryRepository salaryRepo;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Salary> getAllSalary() {
		// TODO Auto-generated method stub
		return  salaryRepo.findAll();
	}

	@Override
	public Salary addUpdateSalary(Salary salary, int employeeId) {
		// TODO Auto-generated method stub
		employeeExist(employeeId);
		
		
		
		if(salary.getSalaryId()!=0 && salaryRepo.findById(salary.getSalaryId()).get()==null) {
			throw new RuntimeException("Salary Not Exist with given id");
		}
		
		if(salary.getGrossSalary()<salary.getDeductions()) {
			System.out.println("gross need to be greater than deductions");
			throw new RuntimeException("gross need to be greater than deductions");
		}
		
		salary.setNetSalary(salary.getGrossSalary()-salary.getDeductions());
		Employee employee = employeeRepository.findById(employeeId).get();
		salary.setEmployee(employee);
		SalaryTransaction transaction;
		if(salary.getTransaction()==null) {
			salary.setTransaction(new SalaryTransaction());
		     
		}
		
		transaction=salary.getTransaction();
		
		transaction.setAccount(employee.getAccount());
		transaction.setAmount(salary.getNetSalary());
		Salary dbSalary=salaryRepo.save(salary);
		transaction.setSalary(dbSalary);
		return  salaryRepo.save(salary);
		
		
	}

	@Override
	public String deletSalary(int salaryId) {
		// TODO Auto-generated method stub
		if(salaryRepo.findById(salaryId).get()==null) {
			throw new RuntimeException("Salary Not Exist with given id");
		}
		
		salaryRepo.deleteById(salaryId);
		return "salary deleted";
	}

	@Override
	public SalaryTransaction  getSalaryTransaction(int salaryId) {
		// TODO Auto-generated method stub
		if( salaryRepo.findById(salaryId).get()==null) {
			throw new RuntimeException("Salary Not Exist with given id");
		}
		SalaryTransaction transaction = salaryRepo.findById(salaryId).get().getTransaction();
		
		
		return transaction;
	}

	@Override
	public SalaryTransaction updateSalaryTransaction(int salaryId,SalaryTransaction transaction) {
		
		// TODO Auto-generated method stub
		if( salaryRepo.findById(salaryId).get()==null) {
			throw new RuntimeException("Salary Not Exist with given id");
		}
		Salary salary=salaryRepo.findById(salaryId).get();
		SalaryTransaction dbTransaction = salaryRepo.findById(salaryId).get().getTransaction();
		
		dbTransaction.setAmount(transaction.getAmount());
		dbTransaction.setTransactionDate(transaction.getTransactionDate());
		dbTransaction.setStatus(transaction.getStatus());
		salary.setTransaction(dbTransaction);
		salaryRepo.save(salary);
		
		
		return salary.getTransaction();
	}
	
	private void employeeExist(int employeeId) {
		 Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		    if (!employeeOpt.isPresent()) {
		        throw new RuntimeException("Employee not found for id: " + employeeId);
		    }
	}

}
