package com.user.yesbank.service.client;

import com.user.yesbank.dto.BankAccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class BankAccountFallback implements BankAccountsFeignClient {
    @Override
    public ResponseEntity<BankAccountDTO> fetchBankAccountDetails( String correlationId, @RequestParam Long bankId) {
        return null;
    }
}


