package com.techlab.company.entity;

import java.sql.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 1, message = "First name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only alphabetic characters")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 1, message = "Last name must have at least 1 character")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only alphabetic characters")
    @Column(name = "lastName")
    private String lastName;

    @NotNull(message = "Phone number cannot be null")
    @Digits(integer = 10, fraction = 0, message = "Phone number must be a 10-digit number")
    @Column(name = "phoneNumber")
    private long phoneNumber;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    @NotNull(message = "Position cannot be null")
    private EmployeePosition position;

    @Column(name = "hireDate")
    @NotNull(message = "Hire date cannot be null")
    @PastOrPresent(message = "Hire date must be in the past or present")
    private Date hireDate;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    @Column(name = "salary")
    private double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber")
    private SalaryAccount account;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Employment status cannot be null")
    private EmploymentStatus status;
}
