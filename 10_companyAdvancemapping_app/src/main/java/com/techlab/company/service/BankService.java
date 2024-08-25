package com.techlab.company.service;

import java.util.List;

import com.techlab.company.entity.Bank;
import com.techlab.company.entity.SalaryAccount;

public interface BankService {
	
	Bank addBank(Bank bank);

	List<SalaryAccount> getAllAccounts();

	List<Bank> getAllBanks();

	List<SalaryAccount> getAllAccountsByBankId(int bankId);

}
