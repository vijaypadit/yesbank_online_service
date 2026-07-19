package com.user.yesbank.dto;

import com.user.yesbank.enums.AccountStatus;
import com.user.yesbank.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountDTO {

    private String accountNumber;

    private String ifscCode;

    private AccountType type;

    private BigDecimal balance;

    private AccountStatus status;

    private Long bankId;
}
