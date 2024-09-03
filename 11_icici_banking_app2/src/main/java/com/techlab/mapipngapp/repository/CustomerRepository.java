package com.techlab.mapipngapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Customer;
import com.techlab.mapipngapp.entity.User;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	
	Customer  findByUser(User user);
	
	

}
