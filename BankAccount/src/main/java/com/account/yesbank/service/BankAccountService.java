package com.account.yesbank.service;

import com.account.yesbank.dto.BankAccountDTO;

import java.util.List;

public interface BankAccountService {

    BankAccountDTO createAccount(BankAccountDTO dto);

    BankAccountDTO getByBankId(Long bankId);

    List<BankAccountDTO> getAll();

    BankAccountDTO updateAccount(int id, BankAccountDTO dto);

    BankAccountDTO updateStatus(int id, String status);

    void deleteAccount(int id);
}