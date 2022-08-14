package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_COINS")
public class CoinModel implements Serializable {

    @Id
    private String id;
    @Column(nullable = false, unique = true)
    private String symbol;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private BigDecimal priceUsd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoinModel coinModel)) return false;

        if (!getId().equals(coinModel.getId())) return false;
        if (!getSymbol().equals(coinModel.getSymbol())) return false;
        return getName().equals(coinModel.getName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getSymbol().hashCode();
        result = 31 * result + getName().hashCode();
        return result;
    }
}
