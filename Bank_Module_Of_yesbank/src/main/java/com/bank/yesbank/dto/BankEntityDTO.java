package com.bank.yesbank.dto;

import com.bank.yesbank.enums.BankStatus;
import com.bank.yesbank.enums.CountryType;
import com.bank.yesbank.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankEntityDTO {
    private Long bankId; // reference only
    private String name;
    @Column(unique = true, nullable = false)
    private String code; // unique bank code
    private String branchAddress;
    private String phoneNumber;
    private String email;
    private String website;
    @Enumerated(EnumType.STRING)
    private CountryType country;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @Enumerated(EnumType.STRING)
    private BankStatus status;

}
