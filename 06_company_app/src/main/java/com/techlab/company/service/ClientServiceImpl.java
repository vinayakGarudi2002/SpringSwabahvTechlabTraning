package com.techlab.company.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.company.entity.Client;
import com.techlab.company.entity.ClientStatus;
import com.techlab.company.entity.KycStatus;
import com.techlab.company.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Override
	public Page<Client> getAllClients(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return clientRepo.findAll(pageable);
	}

	@Override
	public String addUpdateClient(Client client) {
	  // Check if the client is new (client id is 0)
	  if (client.getClientId() == 0) {
	    // Set creation date to current date
	    client.setCreationDate(new Date(System.currentTimeMillis()));
	  }
	  
	  clientRepo.save(client);
	  return "Added Succesfully";
	}

	@Override
	public String deleteClient(int clientId) {
		// TODO Auto-generated method stub
		clientRepo.deleteById(clientId);
		return "deleted succesfully";
	}

	@Override
	public Page<Client> getClientsByStatus(ClientStatus status,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return clientRepo.findByStatus(status,pageable);
	}

	@Override
	public Page<Client> getClientsByKycStatus(KycStatus kycStatus,int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return clientRepo.findByKycStatus(kycStatus,pageable);
	}

	@Override
	public Page<Client> getClientsByCompanyName(String companyName,int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return clientRepo.findByCompanyName(companyName,pageable);
	}

	@Override
	public Client getClientById(int clienyId) {
		// TODO Auto-generated method stub
		return clientRepo.findById(clienyId).get();
	}

}
