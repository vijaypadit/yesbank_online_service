package com.user.yesbank.service.client;

import com.user.yesbank.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class CardsFallback implements CardsFeignClint{
   public ResponseEntity<CardsDTO> fetchCardsDetails( String correlationId, @RequestParam Long bankId){
      return null;
    }

}
