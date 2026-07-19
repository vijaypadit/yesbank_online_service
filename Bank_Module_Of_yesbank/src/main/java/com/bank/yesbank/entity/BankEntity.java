package com.bank.yesbank.entity;

import com.bank.yesbank.enums.BankStatus;
import com.bank.yesbank.enums.CountryType;
import com.bank.yesbank.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "banks")
public class BankEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId; // reference only
    private String name;
    @Column(unique = true, nullable = false)
    private String code; // unique bank code
    private String branchAddress;
    private String phoneNumber;
    private String email;
    private String website;
    @Enumerated(EnumType.STRING)
    private CountryType country;          // ✅ Enum used here
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;   // ✅ Enum used here
    @Enumerated(EnumType.STRING)
    private BankStatus status;       // ✅ Enum used here
}