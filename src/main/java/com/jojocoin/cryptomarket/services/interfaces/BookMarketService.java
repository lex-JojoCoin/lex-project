package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.PurchaseOrderRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleOrderRequestDto;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.models.OrderModel;

public interface BookMarketService {

    BookMarketModel findBook(Long id);
    BookMarketModel save();
    BookMarketModel createSaleOrder(SaleOrderRequestDto request);
    BookMarketModel createPurchaseOrder(PurchaseOrderRequestDto request);
    OrderModel findOrder(Long orderId);
    void closeOrder(OrderModel order);
}
