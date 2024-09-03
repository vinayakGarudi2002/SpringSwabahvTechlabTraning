package com.techlab.jwtsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.jwtsecurity.entity.Customer;
import com.techlab.jwtsecurity.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		
		return  customerRepository.findById(customerId).orElseThrow(()-> new RuntimeException("Customer Not Found"));
	}

}
