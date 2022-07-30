package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ClientModelNotFoundException;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponseDto> findAll() {
        List<ClientModel> modelList = clientRepository.findAll();
        List<ClientResponseDto> response = new ArrayList<>();

        for (ClientModel model : modelList) {
            ClientResponseDto responseDto = new ClientResponseDto();
            BeanUtils.copyProperties(model, responseDto);
            response.add(responseDto);
        }
        return response;
    }

    @Override
    public ClientResponseDto findById(UUID id) {
        ClientModel clientModel = findClientModel(id);

        ClientResponseDto clientResponseDto = new ClientResponseDto();
        BeanUtils.copyProperties(clientModel, clientResponseDto);
        return clientResponseDto;
    }

    @Override
    public ClientResponseDto save(ClientRequestDto request) {
        ClientModel clientModel = new ClientModel();

        BeanUtils.copyProperties(request, clientModel);
        clientRepository.save(clientModel); //Como salvar sem campos nulos?

        ClientResponseDto response = new ClientResponseDto();
        BeanUtils.copyProperties(clientModel, response);
        return response;
    }

    @Override
    public ClientResponseDto update(UUID id, ClientRequestDto request) {
        ClientModel clientModel = findClientModel(id);

        BeanUtils.copyProperties(request, clientModel);
        clientRepository.save(clientModel);

        ClientResponseDto response = new ClientResponseDto();
        BeanUtils.copyProperties(clientModel, response);
        return response;
    }

    @Override
    public void delete(UUID id) {
        findClientModel(id);
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }
    }

    private ClientModel findClientModel(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientModelNotFoundException(id));
    }
}
