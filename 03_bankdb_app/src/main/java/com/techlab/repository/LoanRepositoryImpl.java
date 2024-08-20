package com.techlab.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlab.entity.Loan;
import com.techlab.entity.LoanStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Loan> getAllLoans() {
        TypedQuery<Loan> query = manager.createQuery("select l from Loan l", Loan.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Loan getLoanById(int loanId) {
        Loan loan = manager.find(Loan.class, loanId);
        if (loan == null) {
            System.out.println("Loan not found with ID: " + loanId);;
        }
        return loan;
    }

    @Override
    @Transactional
    public void addLoan(Loan loan) {
        manager.persist(loan);
    }

    @Override
    @Transactional
    public void deleteLoanById(int loanId) {
        Query query = manager.createQuery("Delete from Loan l where l.loanId = :id");
        query.setParameter("id", loanId);
        int rowsDeleted = query.executeUpdate();
        if (rowsDeleted == 0) {
            System.out.println("Loan not found with ID: " + loanId);
        }
    }

    @Override
    @Transactional
    public void updateLoan(Loan loan) {
        Loan existingLoan = manager.find(Loan.class, loan.getLoanId());
        if (existingLoan != null) {
            manager.merge(loan);
        } else {
            System.out.println("Loan not found for update with ID: " + loan.getLoanId());
        }
    }

    @Override
    public List<Loan> getLoanByStatus(LoanStatus status) {
        TypedQuery<Loan> query = manager.createQuery("select l from Loan l where l.loanStatus = :status", Loan.class);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
