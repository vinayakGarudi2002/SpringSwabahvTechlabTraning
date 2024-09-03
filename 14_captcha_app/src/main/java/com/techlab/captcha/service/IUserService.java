package com.techlab.captcha.service;

import java.util.List;
import java.util.Optional;

import com.techlab.captcha.entity.User;

public interface IUserService {

	void createUser(User user);
	List<User> getAllUsers();
	Optional<User> getOneUser(Integer Id);
}