package com.jojocoin.cryptomarket.models;


import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_ORDER")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private OrderType type;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private BigDecimal value;

    @OneToOne
    @JoinColumn(name = "coin_id")
    private CoinModel coin;
}
