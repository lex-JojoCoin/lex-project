package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.PurchaseRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleRequestDto;
import com.jojocoin.cryptomarket.models.ExchangeModel;

public interface ExchangeService {

    ExchangeModel purchaseCoins(PurchaseRequestDto request);
    ExchangeModel sellCoins(SaleRequestDto request);

}
