package com.jojocoin.cryptomarket.dtos.response;

import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.OrderModel;

import java.math.BigDecimal;
import java.util.List;

public class OrderResponseDto {

    private ClientResponseDto client;
    private OrderStatus status;
    private OrderType type;
    private Integer amount;
    private BigDecimal value;
    private CoinModel coin;

    public OrderResponseDto(OrderModel model) {
        this.client = new ClientResponseDto(model.getClient());
        this.status = model.getStatus();
        this.type = model.getType();
        this.amount = model.getAmount();
        this.value = model.getValue();
        this.coin = model.getCoin();
    }
}
