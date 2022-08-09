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
    private Long id;
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

    public void closeOrder(){
        this.status = OrderStatus.CLOSED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderModel that)) return false;

        if (!getId().equals(that.getId())) return false;
        if (!getClient().equals(that.getClient())) return false;
        if (getStatus() != that.getStatus()) return false;
        if (getType() != that.getType()) return false;
        if (!getAmount().equals(that.getAmount())) return false;
        if (!getValue().equals(that.getValue())) return false;
        return getCoin().equals(that.getCoin());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getClient().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getValue().hashCode();
        result = 31 * result + getCoin().hashCode();
        return result;
    }
}
