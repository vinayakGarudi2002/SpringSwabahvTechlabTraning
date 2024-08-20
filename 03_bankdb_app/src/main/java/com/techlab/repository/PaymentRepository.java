package com.techlab.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.techlab.entity.Payment;
import com.techlab.entity.PaymentMode;
import com.techlab.entity.PaymentStatus;


public interface PaymentRepository {

	List<Payment> getAllPayments();

	Payment getPaymentById(int paymentId);

	void addPayment(Payment payment);

	void deletePaymentById(int paymentId);

	void updatePayment(Payment payment);

	List<Payment> getPaymentsByStatus(PaymentStatus status);
	
	List<Payment> getPaymentsByMode(PaymentMode mode);

}
