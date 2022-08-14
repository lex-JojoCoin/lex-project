package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.ExchangeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExchangeRepository extends JpaRepository<ExchangeModel, Long> {
}
