package com.techlab.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.company.entity.Salary;
import com.techlab.company.entity.SalaryTransaction;
import com.techlab.company.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companyapp")
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@GetMapping("/salary")
	public List<Salary> getAllSalary(){
		return salaryService.getAllSalary();
	}
	
	
	
	@PostMapping("/salary")
	public Salary addUpdateSalary(@Valid @RequestBody Salary salary, @RequestParam int employeeId) {
		return salaryService.addUpdateSalary(salary,employeeId);
	}
	
	
	@PutMapping("/salary/transaction")
	public SalaryTransaction updateSalaryTransaction(@RequestParam int salaryId ,@Valid @RequestBody SalaryTransaction transaction) {
		return salaryService.updateSalaryTransaction(salaryId, transaction);
	}
	
	@GetMapping("/salary/transaction")
	public SalaryTransaction getSalaryTransaction(@RequestParam int salaryId ) {
		return salaryService.getSalaryTransaction(salaryId);
	}
	
	@DeleteMapping("/salary")
	public String deleteSalaryById(@RequestParam  int salaryId) {
		return salaryService.deletSalary(salaryId);
	}


}
