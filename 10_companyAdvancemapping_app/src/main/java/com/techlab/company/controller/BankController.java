package com.techlab.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.company.entity.Bank;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.service.BankService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/companyapp")
public class BankController {
	
	
	@Autowired
	private BankService bankService;
	
	
	@PostMapping("/bank")
	public Bank addBank(@Valid @RequestBody Bank bank) {
		
		return bankService.addBank(bank);
	}
	
	@GetMapping("/bank")
	public List<Bank> getAllBanks(){
		return bankService.getAllBanks();
	}
	
	@GetMapping("/bank/account")
	public List<SalaryAccount> getAllBankAccounts(){
		return bankService.getAllAccounts();
	}
	
	@GetMapping("/bank/account/{bankId}")
	public List<SalaryAccount> getAllBankAccountsById(@PathVariable int bankId){
		return bankService.getAllAccountsByBankId(bankId);
	}


}
