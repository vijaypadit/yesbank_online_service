package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.enums.CardStatus;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardsServiceImpl implements ICardsService {
    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public void createCard(CardsDto dto) {

        if (cardsRepository.findByBankId(dto.getBankId()).isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for userId " + dto.getBankId());
        }

        Cards card = new Cards();

        card.setBankId(dto.getBankId());
        card.setCardNumber(generateCardNumber());
        card.setCardType(dto.getCardType());

        // ✅ Dynamic limit
        BigDecimal limit = dto.getTotalLimit() != null
                ? dto.getTotalLimit()
                : BigDecimal.valueOf(100000);

        card.setTotalLimit(limit);
        card.setAmountUsed(BigDecimal.ZERO);
        card.setAvailableAmount(limit);

        card.setStatus(CardStatus.ACTIVE);

        cardsRepository.save(card);
    }

    @Override
    public CardsDto fetchCard(Long bankId) {

        Cards card = cardsRepository.findByBankId(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "userId", bankId.toString()));

        return CardsMapper.mapToCardsDto(card, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto dto) {

        Cards card = cardsRepository.findByCardNumber(dto.getCardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "cardNumber", dto.getCardNumber()));

        CardsMapper.mapToCards(dto, card);

        // ✅ Business Rule
        card.setAvailableAmount(card.getTotalLimit().subtract(card.getAmountUsed()));

        cardsRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(Long bankId) {

        Cards card = cardsRepository.findByBankId(bankId)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "userId", bankId.toString()));

        cardsRepository.delete(card);
        return true;
    }

    // ✅ Card Number Generator
    private String generateCardNumber() {
        return "CARD" + System.currentTimeMillis();
    }
}