package com.techlab.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loanId")
    private int loanId;

    @Column(name = "loanAmount")
    private double loanAmount;

    @Column(name = "interestRate")
    private double interestRate;

    @Column(name = "loanTerm")
    private int loanTerm; //  this is in months 

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "loanStatus")
    private LoanStatus loanStatus;

    public Loan() {
        super();
    }

    public Loan(int loanId, double loanAmount, double interestRate, int loanTerm, Date startDate, Date endDate, LoanStatus loanStatus) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.loanStatus = loanStatus;
    }

    // Getters and Setters

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "Loan [loanId=" + loanId + ", loanAmount=" + loanAmount + ", interestRate=" + interestRate + ", loanTerm=" 
            + loanTerm + ", startDate=" + startDate + ", endDate=" + endDate + ", loanStatus=" + loanStatus + "]";
    }

    
}
