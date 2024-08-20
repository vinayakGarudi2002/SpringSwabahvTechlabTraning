package com.techlab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.entity.Loan;
import com.techlab.entity.LoanStatus;


public interface LoanService {
List<Loan> getAllLoans();
	
	Loan getLoanById(int LoanId);
//	
	List<Loan> getLoanByStatus(LoanStatus status);
	
	void addLoan(Loan Loan);
	
	void deleteLoanById(int LoanId);
	
	void updateLoan(Loan Loan);
}
