package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDto;

public interface ICardsService {

    void createCard(CardsDto dto);

    CardsDto fetchCard(Long bankId);

    boolean updateCard(CardsDto dto);

    boolean deleteCard(Long bankId);
}
