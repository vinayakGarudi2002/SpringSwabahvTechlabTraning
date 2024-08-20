package com.techlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.entity.Loan;
import com.techlab.entity.LoanStatus;
import com.techlab.service.LoanService;

@RestController
@RequestMapping("/bankdbapp")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/loans")
    public ResponseEntity<List<Loan>> getAllLoans() {
//    	if(status==null) {
        return ResponseEntity.ok(loanService.getAllLoans());
//    	}
//    	 return ResponseEntity.ok(loanService.getLoanByStatus(status));
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<Loan> getLoanById(@PathVariable int loanId) {
        return ResponseEntity.ok(loanService.getLoanById(loanId));
    }

    @PostMapping("/loan")
    public String addLoan(@RequestBody Loan loan) {
        loanService.addLoan(loan);
        return "Loan added successfully";
    }

    @PutMapping("/loan")
    public String updateLoan(@RequestBody Loan loan) {
        loanService.updateLoan(loan);
        return "Loan updated successfully";
    }

    @DeleteMapping("/loan/{loanId}")
    public String deleteLoan(@PathVariable int loanId) {
        loanService.deleteLoanById(loanId);
        return "Loan deleted successfully";
    }

    
  @GetMapping("/loans/status/")
  public ResponseEntity<List<Loan>> getLoanByStatus(@RequestParam LoanStatus status) {
      return ResponseEntity.ok(loanService.getLoanByStatus(status));
  }
}
