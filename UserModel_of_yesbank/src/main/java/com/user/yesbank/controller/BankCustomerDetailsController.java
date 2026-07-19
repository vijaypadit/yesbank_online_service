package com.user.yesbank.controller;
import com.user.yesbank.dto.BankCustomerDetailsDTO;
import com.user.yesbank.service.BankCustomerDetailsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class BankCustomerDetailsController {

    private static final Logger logger = LoggerFactory.getLogger(BankCustomerDetailsController.class);

    @Autowired
    private BankCustomerDetailsService bankCustomerDetailsService;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<BankCustomerDetailsDTO> fetchCustomerDetails(@RequestHeader("yesbank-correlation-id") String correlationId, @RequestParam Long bankId) {

        logger.debug("yesbank-correlation-id found: {} ", correlationId);

        BankCustomerDetailsDTO dto = bankCustomerDetailsService.fetchBankCustomerDetails(bankId, correlationId);

        return ResponseEntity.ok(dto);
    }
}