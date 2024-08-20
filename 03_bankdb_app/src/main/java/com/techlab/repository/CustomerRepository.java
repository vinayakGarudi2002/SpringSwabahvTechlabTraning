package com.techlab.repository;

import java.util.List;

import com.techlab.entity.Customer;

public interface CustomerRepository {
	
List<Customer> getAllCustomers();
	
	Customer getCustomerById(int customerId);
//	
//	List<Customer> getCustomerByName(String name);
	
	void addCustomer(Customer customer);
	
	void deleteCustomerById(int customerId);
	
	void updateCustomer(Customer customer);
	

}
