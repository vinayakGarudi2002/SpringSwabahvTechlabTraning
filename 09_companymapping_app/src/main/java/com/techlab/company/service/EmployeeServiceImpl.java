package com.techlab.company.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import com.techlab.company.dto.PageResponse;
import com.techlab.company.entity.Department;
import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.repository.DepartmentRepository;
import com.techlab.company.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

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
        	throw new RuntimeException();
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
        return employeeRepository.findById(employeeId).orElse(null);
    }

	@Override
	public SalaryAccount getSalaryAccountById(int emeployeeId) {
		// TODO Auto-generated method stub
		if(emeployeeId!=0&& employeeRepository.findById(emeployeeId).get()==null) {
        	throw new RuntimeException();
        }
		return employeeRepository.findById(emeployeeId).get().getAccount();
	}

	@Override
	public SalaryAccount updateSalaryAccount(int employeeId, SalaryAccount account) {
	    // Check if the employee exists
	    Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
	    if (!employeeOpt.isPresent()) {
	        throw new RuntimeException("Employee not found for id: " + employeeId);
	    }

	    Employee employee = employeeOpt.get();
	    SalaryAccount dbAccount = employee.getAccount();

	  
	    dbAccount.setAccountHolderName(account.getAccountHolderName());

	 
	    employee.setAccount(dbAccount);

	   
	    employeeRepository.save(employee);

	    return dbAccount;
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

}
