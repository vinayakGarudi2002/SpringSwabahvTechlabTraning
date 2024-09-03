package com.techlab.captcha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.captcha.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}