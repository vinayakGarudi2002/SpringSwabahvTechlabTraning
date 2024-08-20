package com.techlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.entity.Customer;
import com.techlab.entity.Loan;
import com.techlab.entity.LoanStatus;
import com.techlab.repository.CustomerRepository;
import com.techlab.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private LoanRepository loanRepo;

	@Override
	public List<Loan> getAllLoans() {
		// TODO Auto-generated method stub
		return loanRepo.getAllLoans();
	}

	@Override
	public Loan getLoanById(int LoanId) {
		// TODO Auto-generated method stub
		return loanRepo.getLoanById(LoanId);
	}

	@Override
	public List<Loan> getLoanByStatus(LoanStatus status) {
		// TODO Auto-generated method stub
		return loanRepo.getLoanByStatus(status);
	}

	@Override
	public void addLoan(Loan Loan) {
		// TODO Auto-generated method stub
		loanRepo.addLoan(Loan);
	}

	@Override
	public void deleteLoanById(int LoanId) {
		// TODO Auto-generated method stub
		loanRepo.deleteLoanById(LoanId);
	}

	@Override
	public void updateLoan(Loan Loan) {
		// TODO Auto-generated method stub
		loanRepo.updateLoan(Loan);
	}



	

}
