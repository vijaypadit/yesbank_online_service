package com.account.yesbank.mapper;


import com.account.yesbank.dto.BankAccountDTO;
import com.account.yesbank.entity.BankAccountEntity;

import java.util.Random;

public class AccountMapper {
    public static BankAccountEntity mapToBankAccountEntity(BankAccountEntity bankAccountEntity, BankAccountDTO bankAccountDTO) {
        bankAccountEntity.setAccountNumber(bankAccountDTO.getAccountNumber());
        bankAccountEntity.setIfscCode(bankAccountDTO.getIfscCode());
        bankAccountEntity.setType(bankAccountDTO.getType());
        bankAccountEntity.setBalance(bankAccountDTO.getBalance());
        bankAccountEntity.setStatus(bankAccountDTO.getStatus());
        bankAccountEntity.setBankId(bankAccountDTO.getBankId());
        // Business Defaults
        bankAccountEntity.setBalance(bankAccountDTO.getBalance());
        bankAccountEntity.setStatus(bankAccountDTO.getStatus());
        bankAccountEntity.setAccountNumber(generateAccountNumber());
        return bankAccountEntity;
    }
    public static BankAccountDTO mapToBankAccount(BankAccountDTO bankAccountDTO,BankAccountEntity bankAccountEntity) {
        bankAccountDTO.setAccountNumber(bankAccountEntity.getAccountNumber());
        bankAccountDTO.setIfscCode(bankAccountEntity.getIfscCode());
        bankAccountDTO.setType(bankAccountEntity.getType());
        bankAccountDTO.setBalance(bankAccountEntity.getBalance());
        bankAccountDTO.setStatus(bankAccountEntity.getStatus());
        bankAccountDTO.setBankId(bankAccountEntity.getBankId());
        //business default
        bankAccountDTO.setBalance(bankAccountEntity.getBalance());
        bankAccountDTO.setStatus(bankAccountEntity.getStatus());
        bankAccountDTO.setAccountNumber(generateAccountNumber());
        return bankAccountDTO;
}
public static String generateAccountNumber() {
    int min = 10000000; // smallest 8-digit number
    int max = 99999999; // largest 8-digit number
    int randomNumber = (int) (Math.random() * (max - min + 1)) + min;
    return "YES" + randomNumber;
}

}
