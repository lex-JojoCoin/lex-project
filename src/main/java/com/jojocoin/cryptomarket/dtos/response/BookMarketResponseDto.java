package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.models.ClientModel;

import java.util.List;

public class BookMarketResponseDto {

    private List<OrderResponseDto> purchaseOrders;
    private List<OrderResponseDto> saleOrders;

    public BookMarketResponseDto(BookMarketModel model) {
        this.purchaseOrders = model.getPurchaseOrders().stream().map(OrderResponseDto::new).toList();
        this.saleOrders = model.getPurchaseOrders().stream().map(x -> new OrderResponseDto(x)).toList();
    }
}
