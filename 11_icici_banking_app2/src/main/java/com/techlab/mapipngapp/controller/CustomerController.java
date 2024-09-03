package com.techlab.mapipngapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.mapipngapp.dto.CreateCustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerAccountDto;
import com.techlab.mapipngapp.dto.CustomerDetailDto;
import com.techlab.mapipngapp.dto.PageResponse;
import com.techlab.mapipngapp.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {
	
    @Autowired
    private CustomerService customerService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/customer")
    public ResponseEntity<CreateCustomerAccountDto> addCustomer(@RequestBody @Valid CreateCustomerAccountDto createAccount) {
        return ResponseEntity.ok(customerService.addCustomer(createAccount));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customer")
    public ResponseEntity<PageResponse<CustomerAccountDto>> getAllCustomerAccounts(
            @Valid
            @RequestParam(required = false) Integer pageNo,
            @Valid
            @RequestParam(required = false) Integer pageSize) {
        
        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return ResponseEntity.ok(customerService.getAllCustomer(pageNo, pageSize));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers")
    public ResponseEntity<PageResponse<CustomerDetailDto>> getAllCustomersDetail(
            @Valid
            @RequestParam(required = false) Integer pageNo,
            @Valid
            @RequestParam(required = false) Integer pageSize) {

        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return ResponseEntity.ok(customerService.getAllCustomersDetail(pageNo, pageSize));
    }

    
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerDetailDto> getCustomerById(@PathVariable int customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

  
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/customers/withoutaccount")
    public ResponseEntity<PageResponse<CustomerDetailDto>> getAllCustomersWithoutAccount(
            @Valid
            @RequestParam(required = false) Integer pageNo,
            @Valid
            @RequestParam(required = false) Integer pageSize) {

        pageNo = pageNo == null ? 0 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        return ResponseEntity.ok(customerService.getAllCustomerWithoutAccount(pageNo, pageSize));
    }
}
