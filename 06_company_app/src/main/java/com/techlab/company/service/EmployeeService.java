package com.techlab.company.service;

import org.springframework.data.domain.Page;

import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;

public interface EmployeeService {

    Page<Employee> getAllEmployees(int pageNo, int pageSize);

    String addOrUpdateEmployee(Employee employee);

    String deleteEmployee(int employeeId);

    Page<Employee> getEmployeesByStatus(EmploymentStatus status, int pageNo, int pageSize);

    Page<Employee> getEmployeesByPosition(EmployeePosition position, int pageNo, int pageSize);

    Employee getEmployeeById(int employeeId);
}
