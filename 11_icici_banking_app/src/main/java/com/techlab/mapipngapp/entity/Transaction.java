package com.techlab.mapipngapp.entity;

import com.techlab.mapipngapp.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    @OneToOne
    @JoinColumn(name = "sender_account_id", referencedColumnName = "account_id")
    private Account senderAccount;

    @OneToOne
    @JoinColumn(name = "receiver_account_id", referencedColumnName = "account_id")
    private Account receiverAccount;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Column(name = "amount")
    private double amount;

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Transaction date must be in the past or present")
    @Column(name = "transaction_date")
    private Date date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transaction type cannot be null")
    @Column(name = "transaction_type")
    private TransactionType transactionType;
    
    
    
}
