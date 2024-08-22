package com.techlab.company.controller;

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

import com.techlab.company.entity.Employee;
import com.techlab.company.entity.EmployeePosition;
import com.techlab.company.entity.EmploymentStatus;
import com.techlab.company.service.EmployeeService;

@RestController
@RequestMapping("/companyapp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public Page<Employee> getAllEmployees(@RequestParam(required = false) Integer pageNo,
                                          @RequestParam(required = false) Integer pageSize,
                                          @RequestParam(required = false) EmployeePosition position,
                                          @RequestParam(required = false) EmploymentStatus status) {

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
    public String addOrUpdateEmployee(@RequestBody Employee employee) {
        return employeeService.addOrUpdateEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }
}
