package com.user.yesbank.dto;

import com.user.yesbank.enums.CardStatus;
import com.user.yesbank.enums.CardType;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CardsDTO {
    private Long bankId;;

    private String cardNumber;

    private CardType cardType;

    private BigDecimal totalLimit;

    private BigDecimal amountUsed;

    private BigDecimal availableAmount;

    private CardStatus status;
}
