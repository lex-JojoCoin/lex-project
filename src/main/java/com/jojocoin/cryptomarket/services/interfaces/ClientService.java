package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.models.ClientModel;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientResponseDto> findAll();
    ClientResponseDto findById(UUID id);
    ClientResponseDto save(ClientRequestDto clientRequestDTO);
    ClientResponseDto update(UUID id, ClientRequestDto clientRequestDTO);
    void delete(UUID id);
}
