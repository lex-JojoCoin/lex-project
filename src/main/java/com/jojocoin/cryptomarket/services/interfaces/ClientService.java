package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.ClientDTO;
import com.jojocoin.cryptomarket.models.ClientModel;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    List<ClientModel> findAll();
    ClientModel findById(UUID id);
    ClientModel save(ClientDTO clientDTO);
    ClientModel update(UUID id, ClientDTO clientDTO);
    void delete(UUID id);
}
