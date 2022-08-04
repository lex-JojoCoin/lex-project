package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.BookMarketRequestDto;
import com.jojocoin.cryptomarket.dtos.request.OrderRequestDto;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import java.util.List;
import java.util.UUID;

public interface BookMarketService {

    List<BookMarketModel> findAll();
    BookMarketModel findById(UUID id);
    BookMarketModel save(BookMarketRequestDto request);
    BookMarketModel update(UUID id, BookMarketRequestDto request);
    void deleteById(UUID id);
}
