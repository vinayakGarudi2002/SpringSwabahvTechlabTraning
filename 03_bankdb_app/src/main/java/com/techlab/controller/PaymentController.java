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

import com.techlab.entity.Payment;
import com.techlab.entity.PaymentMode;
import com.techlab.entity.PaymentStatus;
import com.techlab.service.PaymentService;

@RestController
@RequestMapping("/bankdbapp")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @PostMapping("/payment")
    public String addPayment(@RequestBody Payment payment) {
        paymentService.addPayment(payment);
        return "Payment added successfully";
    }

    @PutMapping("/payment")
    public String updatePayment(@RequestBody Payment payment) {
        paymentService.updatePayment(payment);
        return "Payment updated successfully";
    }

    @DeleteMapping("/payment/{paymentId}")
    public String deletePayment(@PathVariable int paymentId) {
        paymentService.deletePaymentById(paymentId);
        return "Payment deleted successfully";
    }

    @GetMapping("/payments/status/")
    public ResponseEntity<List<Payment>> getPaymentsByStatus(@RequestParam PaymentStatus status) {
        return ResponseEntity.ok(paymentService.getPaymentsByStatus(status));
    }
    
    @GetMapping("/payments/mode/")
    public ResponseEntity<List<Payment>> getPaymentsByMode(@RequestParam PaymentMode mode) {
        return ResponseEntity.ok(paymentService.getPaymentsByMode(mode));
    }

}
