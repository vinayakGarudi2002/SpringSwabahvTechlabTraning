package com.techlab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.entity.Customer;
import com.techlab.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.getAllCustomers();
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.addCustomer(customer);
	}

	@Override
	public void deleteCustomerById(int customerId) {
		// TODO Auto-generated method stub
		customerRepo.deleteCustomerById(customerId);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepo.updateCustomer(customer);
	}

	@Override
	public Customer getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return customerRepo.getCustomerById(customerId);
	}

	

}
