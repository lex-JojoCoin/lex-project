package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.SaleOrderRequestDto;
import com.jojocoin.cryptomarket.models.OrderModel;

import java.util.List;

public interface OrderService {

    List<OrderModel> findAll();
    OrderModel findById(Long id);
    OrderModel save(OrderModel orderModel);
    OrderModel update(Long id, SaleOrderRequestDto request);
    void deleteById(Long id);
}
