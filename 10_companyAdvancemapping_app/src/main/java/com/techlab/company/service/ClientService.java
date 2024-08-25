package com.techlab.company.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.techlab.company.entity.Client;
import com.techlab.company.entity.ClientStatus;
import com.techlab.company.entity.Employee;
import com.techlab.company.entity.KycStatus;

public interface ClientService {
	
	Page<Client> getAllClients(int pageNo,int pageSize);
	
	String addUpdateClient(Client client);
	
	String deleteClient(int clientId);
	
	Page<Client> getClientsByStatus(ClientStatus status,int pageNo,int pageSize);
	
	Page<Client> getClientsByKycStatus(KycStatus kycStatus,int pageNo,int pageSize);
	
	Page<Client> getClientsByCompanyName(String companyName,int pageNo,int pageSize);
	
	Client getClientById(int clienyId);

	List<Employee> getEmployees(int clientId);
	
	


}
