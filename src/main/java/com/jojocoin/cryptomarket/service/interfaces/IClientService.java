package com.jojocoin.cryptomarket.service.interfaces;

import com.jojocoin.cryptomarket.dto.ClientDTO;
import com.jojocoin.cryptomarket.models.ClientModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface IClientService {

    List<ClientModel> findAll();
    ClientModel findById(UUID id);
    ClientModel save(ClientDTO clientDTO);
    ClientModel update(UUID id, ClientDTO clientDTO);
    void delete(UUID id);
}
