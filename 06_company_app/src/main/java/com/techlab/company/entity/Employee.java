package com.techlab.company.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private int employeeId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNumber")
    private long phoneNumber;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private EmployeePosition position;

    @Column(name = "hireDate")
    private Date hireDate;

    @Column(name = "salary")
    private double salary;

    @Column(name = "bankAccountNumber")
    private long bankAccountNumber;

    @Column(name = "bankIfscNumber")
    private String bankIfscNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmploymentStatus status;

}
