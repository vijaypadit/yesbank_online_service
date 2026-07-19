package com.user.yesbank.dto;

import com.user.yesbank.enums.BankStatus;
import com.user.yesbank.enums.CountryType;
import com.user.yesbank.enums.CurrencyType;

import lombok.Data;

@Data
public class BankEnityDTO {
    private Long bankId; // reference only

    private String name;

    private String code; // unique bank code

    private String branchAddress;

    private String phoneNumber;

    private String email;

    private String website;

    private CountryType country;

    private CurrencyType currency;

    private BankStatus status;

}
