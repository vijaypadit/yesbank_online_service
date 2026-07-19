package com.account.yesbank.controller;


import com.account.yesbank.dto.BankAccountDTO;
import com.account.yesbank.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/api/bankaccount", produces = {MediaType.APPLICATION_JSON_VALUE})

public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final BankAccountService service;

    public AccountController(BankAccountService service) {
        this.service = service;
    }

    @PostMapping
    public BankAccountDTO create(@RequestBody BankAccountDTO dto) {
        return service.createAccount(dto);
    }

    @GetMapping("/fetch")
    public BankAccountDTO fetchBankAccountDetails(@RequestHeader("yesbank-correlation-id") String correlationId,
                                  @RequestParam Long bankId) {
        logger.debug("yesbank-correlation-id found: {} ", correlationId);
        return service.getByBankId(bankId);
    }

    @GetMapping("/all")
    public List<BankAccountDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public BankAccountDTO update(@PathVariable int id,
                                 @RequestBody BankAccountDTO dto) {
        return service.updateAccount(id, dto);
    }

    @PatchMapping("/{id}/status")
    public BankAccountDTO updateStatus(@PathVariable int id,
                                       @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteAccount(id);
        return "Account closed successfully";
    }
}