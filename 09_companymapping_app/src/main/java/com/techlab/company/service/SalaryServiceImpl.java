package com.techlab.company.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.techlab.company.entity.Salary;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.entity.SalaryTransaction;
import com.techlab.company.entity.TransactionStatus;
import com.techlab.company.repository.SalaryRepository;

@Service
public class SalaryServiceImpl implements SalaryService {
	
	@Autowired
	private SalaryRepository salaryRepo;

	@Override
	public List<Salary> getAllSalary() {
		// TODO Auto-generated method stub
		return  salaryRepo.findAll();
	}

	@Override
	public Salary addUpdateSalary(Salary salary) {
		// TODO Auto-generated method stub
		
		if(salary.getSalaryId()!=0 && salaryRepo.findById(salary.getSalaryId()).get()==null) {
			throw new RuntimeException();
		}
		
		if(salary.getGrossSalary()<salary.getDeductions()) {
			System.out.println("gross need to be greater than deductions");
			throw new RuntimeException();
		}
		
		salary.setNetSalary(salary.getGrossSalary()-salary.getDeductions());
		return salaryRepo.save(salary);
		
		
	}

	@Override
	public String deletSalary(int salaryId) {
		// TODO Auto-generated method stub
		if(salaryRepo.findById(salaryId).get()==null) {
			throw new RuntimeException();
		}
		
		salaryRepo.deleteById(salaryId);
		return "salary deleted";
	}

	@Override
	public SalaryTransaction  getSalaryTransaction(int salaryId) {
		// TODO Auto-generated method stub
		if( salaryRepo.findById(salaryId).get()==null) {
			throw new RuntimeException();
		}
		SalaryTransaction transaction = salaryRepo.findById(salaryId).get().getTransaction();
		
		
		return transaction;
	}

	@Override
	public SalaryTransaction updateSalaryTransaction(int salaryId,SalaryTransaction transaction) {
		
		// TODO Auto-generated method stub
		if( salaryRepo.findById(salaryId).get()==null) {
			throw new RuntimeException();
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
	
	

}
