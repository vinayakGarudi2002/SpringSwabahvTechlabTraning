package com.techlab.captcha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlab.captcha.entity.User;
import com.techlab.captcha.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
   
	@Autowired
	private UserRepository repo;
    
	@Override
	public void createUser(User user) {

		repo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public Optional<User> getOneUser(Integer id) {
		return repo.findById(id);
	}

}