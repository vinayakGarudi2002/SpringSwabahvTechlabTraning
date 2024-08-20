package com.techlab.repository;

import java.util.List;

import com.techlab.entity.Loan;
import com.techlab.entity.LoanStatus;

public interface LoanRepository {
List<Loan> getAllLoans();
	
	Loan getLoanById(int LoanId);
//	
	List<Loan> getLoanByStatus(LoanStatus status);
	
	void addLoan(Loan Loan);
	
	void deleteLoanById(int LoanId);
	
	void updateLoan(Loan Loan);
}
