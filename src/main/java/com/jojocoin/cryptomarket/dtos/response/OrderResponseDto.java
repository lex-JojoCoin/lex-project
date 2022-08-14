package com.jojocoin.cryptomarket.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.OrderModel;
import lombok.Getter;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

@Getter
public class OrderResponseDto {

    private final Long id;
    private final String client;
    private final OrderStatus status;
    private final OrderType type;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime date;
    private final Integer amount;
    private final String value;
    private final String exchangeFee = "0.5%";
    private final CoinModel coin;

    public OrderResponseDto(OrderModel model) {
        this.id = model.getId();
        this.client = model.getClient().getName();
        this.status = model.getStatus();
        this.type = model.getType();
        this.date = LocalDateTime.now();
        this.amount = model.getAmount();
        this.value = NumberFormat.getCurrencyInstance(Locale.US).format(model.getValue());
        this.coin = model.getCoin();

    }
}
