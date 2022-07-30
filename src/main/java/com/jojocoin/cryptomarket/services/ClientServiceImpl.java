package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.request.UserModelRequestDto;
import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ClientModelNotFoundException;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import com.jojocoin.cryptomarket.services.interfaces.UserService;
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
    private final UserService userService;

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public ClientModel findById(UUID id) {
        return clientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public ClientModel findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(()-> new ResourceNotFoundException(cpf));
    }

    public ClientModel save(ClientRequestDto request) {
        UserModel model = userService.save(
                new UserModelRequestDto(request.getUsername(), request.getPassword()));
        ClientModel clientModel = new ClientModel(
                UUID.randomUUID(),
                request.getName(),
                request.getCpf(),
                null,
                model);
        return clientRepository.save(clientModel);
    }

    public ClientModel update(UUID id, ClientRequestDto request) {
        ClientModel clientModel = findById(id);
        BeanUtils.copyProperties(request, clientModel);
        return clientRepository.save(clientModel);
    }

    public ClientModel update(String cpf, ClientRequestDto request) {
        ClientModel clientModel = findByCpf(cpf);
        BeanUtils.copyProperties(request, clientModel);
        return clientRepository.save(clientModel);
    }

    public void delete(UUID id) {
        ClientModel byId = findById(id);
        try {
            clientRepository.deleteById(byId.getClientId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }
    }

    public void delete(String cpf) {
        ClientModel byCpf = findByCpf(cpf);
        try {
            clientRepository.deleteById(byCpf.getClientId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }
    }

}
