package com.techlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.entity.Customer;
import com.techlab.service.CustomerService;

@RestController
@RequestMapping("/bankdbapp")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId){
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
	
	@PostMapping("/customer")
	public String addCustomer(@RequestBody Customer customer){
		customerService.addCustomer(customer);
		return "customer added Succesfully";
	}
	
	@PutMapping("/customer")
	public String updateCustomer(@RequestBody Customer customer){
		customerService.updateCustomer(customer);
		return "customer updated Succesfully";
	}
	
	@DeleteMapping("/customer/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		customerService.deleteCustomerById(customerId);
		return "customer deleted successfully";
	}
}
