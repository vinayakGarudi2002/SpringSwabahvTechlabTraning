package com.techlab.jwtsecurity.service;

import java.util.List;

import com.techlab.jwtsecurity.entity.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);

}
