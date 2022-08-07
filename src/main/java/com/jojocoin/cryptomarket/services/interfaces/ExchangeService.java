package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.PurchaseRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleRequestDto;
import com.jojocoin.cryptomarket.models.ExchangeModel;
import com.jojocoin.cryptomarket.models.OrderModel;
import com.jojocoin.cryptomarket.models.SaleModel;

public interface ExchangeService {

    ExchangeModel purchaseCoins(PurchaseRequestDto request);
    SaleModel sellCoins(SaleRequestDto request);

}
