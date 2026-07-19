package com.bank.yesbank.service;

import com.bank.yesbank.dto.BankEntityDTO;

import java.util.List;

public interface BankService {

    BankEntityDTO createBank(BankEntityDTO dto);

    BankEntityDTO getBankById(Long bankId);

    List<BankEntityDTO> getAllBanks();

    BankEntityDTO updateBank(Long bankId, BankEntityDTO dto);

    void deleteBank(Long bankId);
}