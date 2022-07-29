package com.jojocoin.cryptomarket.service;

import com.jojocoin.cryptomarket.dto.ClientDTO;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.service.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.service.exceptions.ClientModelNotFoundException;
import com.jojocoin.cryptomarket.service.interfaces.IClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.UUID;

public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientModel findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientModelNotFoundException("Cliente não encontrado"));
    }

    @Override
    public ClientModel save(ClientDTO clientDTO) {
        ClientModel clientModel = new ClientModel();
        BeanUtils.copyProperties(clientDTO, clientModel);
        return clientRepository.save(clientModel);
    }

    @Override
    public ClientModel update(UUID id, ClientDTO clientDTO) {
        ClientModel clientModel = findById(id);
        BeanUtils.copyProperties(clientDTO, clientModel);
        return clientRepository.save(clientModel);
    }

    @Override
    public void delete(UUID id) {
        findById(id);
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException("Não é possível deletar o cliente");
        }
    }
}
