package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.CoinModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderRequestDto {

    @NotNull
    private OrderStatus status;

    @NotNull
    private ClientModel client;

    @NotNull
    private OrderType type;

    @NotNull
    private Integer amount;

    @NotNull
    private BigDecimal value;

    @NotNull
    private CoinModel coin;
}
