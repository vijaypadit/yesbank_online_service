package com.account.yesbank.entity;


import com.account.yesbank.enums.AccountStatus;
import com.account.yesbank.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String accountNumber; // account number

    @Column(nullable = false)
    private String ifscCode;

    @Enumerated(EnumType.STRING)
    private AccountType type;   // ✅ ENUM

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private AccountStatus status; // ✅ ENUM

    private Long bankId; // ✅ reference to Bank Service
}


