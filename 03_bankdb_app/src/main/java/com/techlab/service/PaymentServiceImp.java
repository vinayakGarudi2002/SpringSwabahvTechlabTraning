package com.techlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.entity.Payment;
import com.techlab.entity.PaymentMode;
import com.techlab.entity.PaymentStatus;
import com.techlab.repository.PaymentRepository;

@Service
public class PaymentServiceImp implements PaymentService {
	
	@Autowired
	private PaymentRepository pymentRepo;

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return pymentRepo.getAllPayments();
	}

	@Override
	public Payment getPaymentById(int paymentId) {
		// TODO Auto-generated method stub
		return pymentRepo.getPaymentById(paymentId);
	}

	@Override
	public void addPayment(Payment payment) {
		// TODO Auto-generated method stub
		pymentRepo.addPayment(payment);
	}

	@Override
	public void deletePaymentById(int paymentId) {
		// TODO Auto-generated method stub
		pymentRepo.deletePaymentById(paymentId);
	}

	@Override
	public void updatePayment(Payment payment) {
		// TODO Auto-generated method stub
		pymentRepo.updatePayment(payment);
	}

	@Override
	public List<Payment> getPaymentsByStatus(PaymentStatus status) {
		// TODO Auto-generated method stub
		return pymentRepo.getPaymentsByStatus(status);
	}

	@Override
	public List<Payment> getPaymentsByMode(PaymentMode mode) {
		// TODO Auto-generated method stub
		return pymentRepo.getPaymentsByMode(mode);
	}
	

}
