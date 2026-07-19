package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsContactInf0Dto;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/card", produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin
public class CardsController {
    @Autowired
    private ICardsService service;
    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
//    @Value("${build.version}")
//    private String buildVersion;
//
//    @Autowired
//    private Environment environment;
//
//    @Autowired
//    private CardsContactInf0Dto cardsContactInfoDto;


    @PostMapping("/creates")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody CardsDto dto) {

        service.createCard(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Card created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardsDetails(@RequestHeader("yesbank-correlation-id") String correlationId,
                                          @RequestParam Long bankId) {
        logger.debug("yesbank-correlation-id found: {} ", correlationId);
        return ResponseEntity.ok(service.fetchCard(bankId));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody CardsDto dto) {

        service.updateCard(dto);

        return ResponseEntity.ok(new ResponseDto("200", "Updated successfully"));
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable Long bankId) {

        service.deleteCard(bankId);

        return ResponseEntity.ok(new ResponseDto("200", "Deleted successfully"));
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
//    public ResponseEntity<CardsContactInf0Dto> getContactInfo() {
//        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDto);
//    }
}