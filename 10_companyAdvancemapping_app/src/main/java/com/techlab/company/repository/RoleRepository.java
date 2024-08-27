package com.techlab.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
