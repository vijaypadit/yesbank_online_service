package com.user.yesbank.service.client;

import com.user.yesbank.dto.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFallback.class)
public interface CardsFeignClint {
    @GetMapping("/api/card/fetch")
    ResponseEntity<CardsDTO> fetchCardsDetails(@RequestHeader("yesbank-correlation-id") String correlationId, @RequestParam Long bankId);
}
