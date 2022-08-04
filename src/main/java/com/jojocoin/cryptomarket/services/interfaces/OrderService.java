package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.BookMarketRequestDto;
import com.jojocoin.cryptomarket.dtos.request.OrderRequestDto;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.models.OrderModel;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderModel> findAll();
    OrderModel findById(UUID id);
    OrderModel save(OrderRequestDto request);
    OrderModel update(UUID id, OrderRequestDto request);
    void deleteById(UUID id);
}
