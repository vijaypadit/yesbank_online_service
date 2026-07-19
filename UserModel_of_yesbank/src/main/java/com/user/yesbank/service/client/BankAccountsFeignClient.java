package com.user.yesbank.service.client;

import com.user.yesbank.dto.BankAccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "bankaccounts", fallback = BankAccountFallback.class)
public interface BankAccountsFeignClient {
    @GetMapping("/api/bankaccount/fetch")
    ResponseEntity<BankAccountDTO> fetchBankAccountDetails(@RequestHeader("yesbank-correlation-id") String correlationId, @RequestParam Long bankId);
}

