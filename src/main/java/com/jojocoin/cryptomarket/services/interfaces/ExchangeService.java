package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.PurchaseOrderRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleOrderRequestDto;
import com.jojocoin.cryptomarket.dtos.request.OperationRequest;
import com.jojocoin.cryptomarket.dtos.response.OperationResponse;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.models.ExchangeModel;

import java.util.List;

public interface ExchangeService {

    List<ExchangeModel> findAll();
    ExchangeModel createExchange(BookMarketModel book);
    ExchangeModel findExchange();
    OperationResponse purchaseCoins(OperationRequest request);
    OperationResponse sellCoins(OperationRequest request);
    BookMarketModel createSaleOrder(SaleOrderRequestDto request);
    BookMarketModel createPurchaseOrder(PurchaseOrderRequestDto request);
}
