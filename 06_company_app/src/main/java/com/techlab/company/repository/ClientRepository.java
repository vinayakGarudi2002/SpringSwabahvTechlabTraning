package com.techlab.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.company.entity.Client;
import com.techlab.company.entity.ClientStatus;
import com.techlab.company.entity.KycStatus;

public interface ClientRepository extends JpaRepository<Client, Integer>{

Page<Client> findByStatus( ClientStatus status, Pageable pageable);
	
	Page<Client> findByKycStatus(KycStatus kycStatus, Pageable pageable);
	
	Page<Client> findByCompanyName(String companyName,Pageable pageable);
}
