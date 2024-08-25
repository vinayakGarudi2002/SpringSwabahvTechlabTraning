package com.techlab.company.service;

import java.util.List;

import com.techlab.company.dto.PageResponse;
import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.entity.SalaryTransaction;

import jakarta.validation.Valid;

public interface EmployeeService {

	PageResponse<Employee> getAllEmployees(int pageNo, int pageSize);

    String addOrUpdateEmployee(Employee employee);

    String deleteEmployee(int employeeId);

    PageResponse<Employee> getEmployeesByStatus(EmploymentStatus status, int pageNo, int pageSize);

    PageResponse<Employee> getEmployeesByPosition(EmployeePosition position, int pageNo, int pageSize);

    Employee getEmployeeById(int employeeId);
    
    SalaryAccount getSalaryAccountById(int emeployeeId);
    
    SalaryAccount updateSalaryAccount(int employeeId,SalaryAccount account,int bankId);

	String allocateClientToEmployee(int clientId, List<Integer> employeeIds);

	List<SalaryTransaction> getTransactionOfAccountByEmployeeId(int employeeId);
}
