package com.techlab.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techlab.entity.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Customer> getAllCustomers() {
		
		TypedQuery<Customer> query=manager.createQuery("select s  from Customer s",Customer.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		manager.persist(customer);
	}

	@Override
	@Transactional
	public void deleteCustomerById(int customerId) {
		// TODO Auto-generated method stub
		Query query = manager.createQuery("Delete  from Customer c where c.id = :id");
        query.setParameter("id", customerId);	
        query.executeUpdate();
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
	    
	    Customer existingCustomer = manager.find(Customer.class, customer.getCustomerId());
	    if (existingCustomer != null) {
	        manager.merge(customer);
	    } else {
	        System.out.println("Customer not found for update");
	    }
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Customer customer = manager.find(Customer.class, customerId);
		return customer;
	}


}
