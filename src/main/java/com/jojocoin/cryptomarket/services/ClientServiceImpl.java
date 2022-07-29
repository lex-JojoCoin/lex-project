package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.ClientDTO;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ClientModelNotFoundException;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientModel findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientModelNotFoundException("Cliente não encontrado. ID:" + id));
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
