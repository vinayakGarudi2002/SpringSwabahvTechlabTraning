package com.techlab.company.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "salaries")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salaryId")
    private int salaryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "salaryMonth")
    @NotNull(message = "Salary month cannot be null")
    private SalaryMonth salaryMonth;

    @NotNull
    @Column(name = "grossSalary")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gross salary must be greater than 0")
    private double grossSalary;

    @NotNull
    @Column(name = "deductions")
    @DecimalMin(value = "0.0", inclusive = false, message = "Deductions must be greater than 0")
    private double deductions;

    @Column(name = "netSalary")
    private double netSalary;

    @NotNull
    @Column(name = "paymentDate")
    @NotNull(message = "Payment date cannot be null")
    private Date paymentDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Salary payment status cannot be null")
    private SalaryPaymentStatus status;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transactionId")
    private SalaryTransaction transaction;
}
