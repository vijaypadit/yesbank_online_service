package com.user.yesbank.service.client;

import com.user.yesbank.dto.BankEnityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.RequestParam;

@Component
public class BankFallback implements BanksFeignClient{
    @Override
  public   ResponseEntity<BankEnityDTO> fetchBankDetails( String correlationId, @RequestParam Long bankId){
        return null;
    }
}
