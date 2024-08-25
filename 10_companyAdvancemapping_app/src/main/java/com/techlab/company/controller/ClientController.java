package com.techlab.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.company.entity.Client;
import com.techlab.company.entity.ClientStatus;
import com.techlab.company.entity.Employee;
import com.techlab.company.entity.KycStatus;
import com.techlab.company.service.ClientService;

@RestController
@RequestMapping("/companyapp")
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@GetMapping("/clients")
	public Page<Client> getAllClients(@RequestParam(required = false) Integer pageNo,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) String companyName,@RequestParam(required = false) KycStatus kycStatus,@RequestParam(required = false)  ClientStatus status){
		
		pageNo = pageNo == null ? 0 : pageNo; 
		pageSize = pageSize == null ? 10 : pageSize;
		if(companyName!=null && !companyName.isEmpty()) {
			return clientService.getClientsByCompanyName(companyName,pageNo, pageSize);
		}
		
		if(status!=null) {
			return clientService.getClientsByStatus(status,pageNo, pageSize);
		}
		
		if(kycStatus!=null) {
			return clientService.getClientsByKycStatus(kycStatus,pageNo, pageSize);
		}
		
		return clientService.getAllClients(pageNo, pageSize);
	}
	
	
	@PostMapping("/clients")
	public String addUpdateClient(@RequestBody Client client) {
		return clientService.addUpdateClient(client);
	}
	
	
	@DeleteMapping("/clients/{clientId}")
	public String deleteClient(@PathVariable int clientId) {
		return clientService.deleteClient(clientId);
	}
	
	@GetMapping("/clients/{clientId}")
	public Client getClientById(@PathVariable int clientId ) {
		return clientService.getClientById(clientId);
	}
	
	@GetMapping("/clients/employee/{clientId}")
	public List<Employee> getEmployeesOfClientById(@PathVariable int clientId ) {
		return clientService.getEmployees(clientId);
	}
}
