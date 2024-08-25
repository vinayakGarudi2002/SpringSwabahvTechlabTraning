package com.techlab.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.company.entity.Bank;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.repository.BankRepository;

@Service
public class BankServiceImpl implements BankService{
	
	@Autowired
	private BankRepository bankrepo;

	@Override
	public Bank addBank(Bank bank) {
		// TODO Auto-generated method stub
		return bankrepo.save(bank);
	}

	@Override
	public List<SalaryAccount> getAllAccounts() {
	    return bankrepo.findAll().stream()
	        .flatMap(bank -> bank.getAccounts().stream())  // Flatten the stream of lists of SalaryAccounts into a single stream
	        .toList();
	}
	@Override
	public List<Bank> getAllBanks(){
		return bankrepo.findAll();
	}

	@Override
	public List<SalaryAccount> getAllAccountsByBankId(int bankId) {
		// TODO Auto-generated method stub
		isBankExist(bankId);
		return bankrepo.findById(bankId).get().getAccounts();
	}

	
	private void isBankExist(int bankId) {
		if(bankrepo.findById(bankId).get()==null) {
			throw new  RuntimeException("bank not exist");
		}
	}

}
