package com.jojocoin.cryptomarket.services;


import com.jojocoin.cryptomarket.dtos.request.PurchaseRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleRequestDto;
import com.jojocoin.cryptomarket.models.ExchangeModel;
import com.jojocoin.cryptomarket.repository.ExchangeRepository;
import com.jojocoin.cryptomarket.services.interfaces.BookMarketService;
import com.jojocoin.cryptomarket.services.interfaces.CardService;
import com.jojocoin.cryptomarket.services.interfaces.ExchangeService;
import com.jojocoin.cryptomarket.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository repository;
    private final OrderService orderService;
    private final BookMarketService bookService;
    private final CardService cardService;


    public ExchangeModel purchaseCoins(PurchaseRequestDto request) {
        return null;
    }


    public ExchangeModel sellCoins(SaleRequestDto request) {
        return null;
    }
}
