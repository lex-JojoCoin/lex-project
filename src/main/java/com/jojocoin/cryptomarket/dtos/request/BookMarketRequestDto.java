package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.models.OrderModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookMarketRequestDto {

    @NotNull
    private List<OrderModel> saleOrders;
    @NotNull
    private List<OrderModel> purchaseOrders;
}
