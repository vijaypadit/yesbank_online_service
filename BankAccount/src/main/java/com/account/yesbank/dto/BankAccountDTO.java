package com.account.yesbank.dto;

import com.account.yesbank.enums.AccountStatus;
import com.account.yesbank.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

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
    //same

}

