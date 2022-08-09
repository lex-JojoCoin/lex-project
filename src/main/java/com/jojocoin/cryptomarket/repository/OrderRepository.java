package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

}
