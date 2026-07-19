package com.eazybytes.cards.dto;

import com.eazybytes.cards.enums.CardStatus;
import com.eazybytes.cards.enums.CardType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Schema(name = "Cards",
        description = "Schema to hold Card information"
)
@Data
public class CardsDto {

    private Long bankId;;

    private String cardNumber;

    private CardType cardType;

    private BigDecimal totalLimit;

    private BigDecimal amountUsed;

    private BigDecimal availableAmount;

    private CardStatus status;
}
