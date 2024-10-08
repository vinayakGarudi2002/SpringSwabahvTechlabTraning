package com.techlab.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlab.entity.Payment;
import com.techlab.entity.PaymentMode;
import com.techlab.entity.PaymentStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Payment> getAllPayments() {
        TypedQuery<Payment> query = manager.createQuery("select p from Payment p", Payment.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Payment getPaymentById(int paymentId) {
        Payment payment = manager.find(Payment.class, paymentId);
        if (payment == null) {
            System.out.println("Id not found");
        }
        return payment;
    }

    @Override
    @Transactional
    public void addPayment(Payment payment) {
        manager.persist(payment);
    }

    @Override
    @Transactional
    public void deletePaymentById(int paymentId) {
        Query query = manager.createQuery("Delete from Payment p where p.paymentId = :id");
        query.setParameter("id", paymentId);
        int rowsDeleted = query.executeUpdate();
        if (rowsDeleted == 0) {
           System.out.println("Payment not found with ID: " + paymentId);
        }
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment) {
        Payment existingPayment = manager.find(Payment.class, payment.getPaymentId());
        if (existingPayment != null) {
            manager.merge(payment);
        } else {
           System.out.println("Payment not found for update with ID: " + payment.getPaymentId());
        }
    }

    @Override
    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        TypedQuery<Payment> query = manager.createQuery("select p from Payment p where p.paymentStatus = :status", Payment.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

	@Override
	public List<Payment> getPaymentsByMode(PaymentMode mode) {
		 TypedQuery<Payment> query = manager.createQuery("select p from Payment p where p.paymentMode = :mode", Payment.class);
	        query.setParameter("mode", mode);
	        return query.getResultList();
	}
}
