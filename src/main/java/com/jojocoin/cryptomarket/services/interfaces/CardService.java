package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.models.CardModel;

import java.util.List;

public interface CardService {
    List<CardModel> findAll();
    CardModel findByNumber(String number);
    CardModel save(CardRequestDto request);
    CardModel update(String number, CardRequestDto request);
    void delete(String number);

}
