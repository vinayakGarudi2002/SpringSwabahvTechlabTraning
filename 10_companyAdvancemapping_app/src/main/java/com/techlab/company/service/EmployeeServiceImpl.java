package com.techlab.company.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.techlab.company.dto.PageResponse;
import com.techlab.company.entity.Bank;
import com.techlab.company.entity.Client;
import com.techlab.company.entity.Department;
import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.entity.SalaryTransaction;
import com.techlab.company.repository.BankRepository;
import com.techlab.company.repository.ClientRepository;
import com.techlab.company.repository.DepartmentRepository;
import com.techlab.company.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private BankRepository bankRepo;
    
    
    @Autowired
    private ClientRepository clientRepo;

    @Override
    public PageResponse<Employee> getAllEmployees(int pageNo, int pageSize) {
        return createPageResponse(employeeRepository.findAll(PageRequest.of(pageNo, pageSize)));
    }

    @Transactional
    @Override
    public String addOrUpdateEmployee(Employee employee) {

        boolean isNewEmployee = (employee.getEmployeeId() == 0);
        System.out.println(employee.getEmployeeId());
        

        // Save the employee
        if(employee.getEmployeeId()!=0&& employeeRepository.findById(employee.getEmployeeId()).get()==null) {
        	throw new RuntimeException("Employee does Not exist with given Id");
        }
        
        if(employee.getEmployeeId()!=0) {
        	Employee dbEmployee = employeeRepository.findById(employee.getEmployeeId()).get();
        	employee.setAccount(dbEmployee.getAccount());
        	employee.setClient(dbEmployee.getClient());
        }
        
        employeeRepository.save(employee);

        if (isNewEmployee) {
            Department department = departmentRepository.findByDepartmentName("Transaction Banking");
            if (department != null) {
                department.setEmployeeCount(department.getEmployeeCount() + 1);
                departmentRepository.save(department);
            } else {
                throw new RuntimeException("Department not found");
            }
        }

        return "Employee added successfully!";
    }

    @Override
    public String deleteEmployee(int employeeId) {
    	employeeExist(employeeId);
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return "Employee deleted successfully!";
        } else {
            return "Employee not found!";
        }
    }

    @Override
    public PageResponse<Employee> getEmployeesByStatus(EmploymentStatus status, int pageNo, int pageSize) {
        return createPageResponse(employeeRepository.findByStatus(status, PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public PageResponse<Employee> getEmployeesByPosition(EmployeePosition position, int pageNo, int pageSize) {
        return createPageResponse(employeeRepository.findByPosition(position, PageRequest.of(pageNo, pageSize)));
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
    	employeeExist(employeeId);
        return employeeRepository.findById(employeeId).orElse(null);
    }

	@Override
	public SalaryAccount getSalaryAccountById(int emeployeeId) {
		// TODO Auto-generated method stub
		employeeExist(emeployeeId);
		return employeeRepository.findById(emeployeeId).get().getAccount();
	}

	@Override
	public SalaryAccount updateSalaryAccount(int employeeId, SalaryAccount account , int bankId) {
	    // Check if the employee exists
	    Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
	    if (!employeeOpt.isPresent()) {
	        throw new RuntimeException("Employee not found for id: " + employeeId);
	    }
	    
	    Optional<Bank> bankOpt = bankRepo.findById(bankId);
	    if (!bankOpt.isPresent()) {
	        throw new RuntimeException("Bank not found for id: " + bankId);
	    }

	    Bank dbBank = bankOpt.get();
	    Employee employee = employeeOpt.get();
	    SalaryAccount dbAccount = employee.getAccount();
	    if(dbAccount==null) {
	    	dbAccount=new SalaryAccount();
	    	
	    }

	  
	    dbAccount.setAccountHolderName(account.getAccountHolderName());
	    dbAccount.setBank(dbBank);
	    
	    

	 
	    employee.setAccount(dbAccount);

	   
	   
	    
	  
	    
	    return employeeRepository.save(employee).getAccount();

	   
	}
	
	 private PageResponse<Employee> createPageResponse(Page<Employee> paymentPage) {
	        PageResponse<Employee> paymentPageResponse = new PageResponse<>();
	        paymentPageResponse.setContent(paymentPage.getContent());
	        paymentPageResponse.setSize(paymentPage.getSize());
	        paymentPageResponse.setTotalElements(paymentPage.getTotalElements());
	        paymentPageResponse.setTotalPages(paymentPage.getTotalPages());
	        paymentPageResponse.setLastPage(paymentPage.isLast());

	        return paymentPageResponse;
	    }

	@Override
	public String allocateClientToEmployee(int clientId, List<Integer> employeeIds) {
		// TODO Auto-generated method stub
		
		clientExist(clientId);
		Client client = clientRepo.findById(clientId).get();
		employeeIds.forEach((e)->{
		employeeExist(e);
		Employee employee = employeeRepository.findById(e).get();
		employee.setClient(client);
		employeeRepository.save(employee);
		
		});
		return null;
	}
	
	private void employeeExist(int employeeId) {
		 Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
		    if (!employeeOpt.isPresent()) {
		        throw new RuntimeException("Employee not found for id: " + employeeId);
		    }
	}
	
	private void clientExist(int clientId) {
		 Optional<Client> clientOpt = clientRepo.findById(clientId);
		    if (!clientOpt.isPresent()) {
		        throw new RuntimeException("Client not found for id: " + clientId);
		    }
	}

	@Override
	public List<SalaryTransaction> getTransactionOfAccountByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub
		employeeExist(employeeId);
		Employee employee = employeeRepository.findById(employeeId).get();
		if(employee.getAccount()==null&& employee.getAccount().getSalaryTransactions()==null ) {
			throw new RuntimeException("No account or yransactions Exist");
		}
		
		
		return employee.getAccount().getSalaryTransactions();
	}

}
