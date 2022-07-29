package com.jojocoin.cryptomarket.repository;

import com.jojocoin.cryptomarket.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientModel, UUID> {
}
