package com.techlab.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlab.entity.Customer;


public interface CustomerService {
List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);
//	
//	List<Customer> getCustomerByName(String name);
	
	void addCustomer(Customer customer);
	
	void deleteCustomerById(int customerId);
	
	void updateCustomer(Customer customer);
	
}
