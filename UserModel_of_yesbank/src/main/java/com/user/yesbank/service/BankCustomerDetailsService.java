package com.user.yesbank.service;

import com.user.yesbank.dto.BankCustomerDetailsDTO;

public interface BankCustomerDetailsService {
    BankCustomerDetailsDTO fetchBankCustomerDetails(Long bankId, String correlationId);

}