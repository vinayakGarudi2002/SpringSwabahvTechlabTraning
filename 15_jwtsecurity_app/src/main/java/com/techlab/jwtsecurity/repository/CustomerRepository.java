package com.techlab.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.jwtsecurity.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	

}
