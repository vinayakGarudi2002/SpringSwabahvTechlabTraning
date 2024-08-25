package com.techlab.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.company.dto.PageResponse;
import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;
import com.techlab.company.entity.SalaryAccount;
import com.techlab.company.entity.SalaryTransaction;
import com.techlab.company.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companyapp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public PageResponse<Employee> getAllEmployees(@RequestParam(required = false) Integer pageNo,
                                          @RequestParam(required = false) Integer pageSize,
                                         @Valid  @RequestParam(required = false) EmployeePosition position,
                                         @Valid  @RequestParam(required = false) EmploymentStatus status) {

        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;

        if (position != null) {
            return employeeService.getEmployeesByPosition(position, pageNo, pageSize);
        }

        if (status != null) {
            return employeeService.getEmployeesByStatus(status, pageNo, pageSize);
        }

        return employeeService.getAllEmployees(pageNo, pageSize);
    }

    @PostMapping("/employees")
    public String addOrUpdateEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addOrUpdateEmployee(employee);
    }
    

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@Valid @PathVariable int employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@Valid @PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
    
    
    @PutMapping("/employees/account")
    public SalaryAccount updateEmployeeSalaryAccount(@Valid @RequestParam int employeeId,@Valid @RequestBody SalaryAccount account,@RequestParam int bankId) {
    	return employeeService.updateSalaryAccount(employeeId, account,bankId);
    }
    
    @GetMapping("/employees/account")
    public SalaryAccount getEmployeeSalaryAccount(@Valid @RequestParam int employeeId) {
    	return employeeService.getSalaryAccountById(employeeId);
    }
    
    @GetMapping("/employees/account/{employeeId}")
    public List<SalaryTransaction> getTransactionOfAccountByEmployeeId(@PathVariable int employeeId) {
    	return employeeService.getTransactionOfAccountByEmployeeId(employeeId);
    }
    
    @PutMapping("/employees/client")
    public String allocateClientToEmployee(@RequestParam int clientId,@RequestBody List<Integer> employeeIds) {
    	employeeService.allocateClientToEmployee(clientId, employeeIds);
    	return "client allocated to employees";
    }
}
