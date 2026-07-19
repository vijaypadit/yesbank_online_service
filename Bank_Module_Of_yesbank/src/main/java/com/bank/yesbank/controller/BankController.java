package com.bank.yesbank.controller;

import com.bank.yesbank.dto.BankEntityDTO;

import com.bank.yesbank.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/bank", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    private final BankService service;

    public BankController(BankService service) {
        this.service = service;
    }


//    @Value("${build.version}")
//    private String buildVersion;
//    @Autowired
//    private Environment environment;
//
//    @Autowired
//    private BankContactInfoDto bankContactInfoDto;


    @PostMapping
    public BankEntityDTO create(@RequestBody BankEntityDTO dto) {
        return service.createBank(dto);
    }

    @GetMapping("/fetch")
    public BankEntityDTO fetchBankDetails(@RequestHeader("yesbank-correlation-id") String correlationId,
                                           @RequestParam Long bankId) {
        logger.info("Correlation Id : {}", correlationId);
        return service.getBankById(bankId);
    }

    @GetMapping("/alldetails")
    public List<BankEntityDTO> getAll() {
        return service.getAllBanks();
    }

    @PutMapping("/{bankId}")
    public BankEntityDTO update(@PathVariable Long bankId, @RequestBody BankEntityDTO dto) {
        return service.updateBank(bankId, dto);
    }

    @DeleteMapping("/{bankId}")
    public String delete(@PathVariable Long bankId) {
        service.deleteBank(bankId);
        return "Bank deactivated successfully";
    }

//    @GetMapping("/build-info")
//    public ResponseEntity<String> getBuildInfo() {
//        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
//    }
//
//
//    @GetMapping("/java-version")
//    public ResponseEntity<String> getJavaVersion() {
//        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
//    }
//
//
//    @GetMapping("/contact-info")
//    public ResponseEntity<BankContactInfoDto> getContactInfo() {
//        return ResponseEntity.status(HttpStatus.OK).body(bankContactInfoDto);
//    }
}