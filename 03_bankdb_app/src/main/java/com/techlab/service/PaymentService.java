package com.techlab.service;

import java.util.List;



import com.techlab.entity.Payment;
import com.techlab.entity.PaymentMode;
import com.techlab.entity.PaymentStatus;


public interface PaymentService {
	List<Payment> getAllPayments();

	Payment getPaymentById(int paymentId);

	void addPayment(Payment payment);

	void deletePaymentById(int paymentId);

	void updatePayment(Payment payment);

	List<Payment> getPaymentsByStatus(PaymentStatus status);
	
	List<Payment> getPaymentsByMode(PaymentMode mode);
}
