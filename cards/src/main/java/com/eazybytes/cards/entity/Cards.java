package com.eazybytes.cards.entity;

import com.eazybytes.cards.enums.CardStatus;
import com.eazybytes.cards.enums.CardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Cards extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private Long bankId;

    @Column(unique = true, nullable = false)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType; // ✅ ENUM

    private BigDecimal totalLimit;

    private BigDecimal amountUsed;

    private BigDecimal availableAmount;

    @Enumerated(EnumType.STRING)
    private CardStatus status; // ✅ NEW

}
