package com.jojocoin.cryptomarket.controller;

import com.jojocoin.cryptomarket.dto.ClientDTO;
import com.jojocoin.cryptomarket.service.interfaces.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final IClientService IClientService;

    public ClientController(IClientService IClientService) {
        this.IClientService = IClientService;
    }

}
