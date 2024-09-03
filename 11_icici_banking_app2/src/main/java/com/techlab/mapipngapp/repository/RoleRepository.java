package com.techlab.mapipngapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.mapipngapp.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Role  findByRoleName(String role_name);

}
