package com.jojocoin.cryptomarket.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "TB_BOOK_MARKET")
public class BookMarketModel implements Serializable {

    @Id
    private Long id;
    @OneToMany
    @JoinColumn(name = "sales_order_id")
    private List<OrderModel> saleOrders;
    @OneToMany
    @JoinColumn(name = "purchase_order_id")
    private List<OrderModel> purchaseOrders;

}
