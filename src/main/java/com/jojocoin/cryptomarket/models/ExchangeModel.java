package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_Exchange")
public class ExchangeModel implements Serializable {

    @Id
    private Long id;
    @Column
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "Book_Id")
    private BookMarketModel bookMarket;

    public ExchangeModel addBalance(BigDecimal value){
        this.balance = balance.add(value);
        return this;
    }
}
