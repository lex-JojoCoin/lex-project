package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.models.BookMarketModel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BookMarketResponseDto {

    private final List<OrderResponseDto> purchaseOrder = new ArrayList<>();
    private final List<OrderResponseDto> saleOrders= new ArrayList<>();

    public BookMarketResponseDto(BookMarketModel model) {
        this.purchaseOrder.addAll(model.getPurchaseOrders().stream().map(OrderResponseDto::new).toList());
        this.saleOrders.addAll(model.getSaleOrders().stream().map(OrderResponseDto::new).toList());
    }
}
