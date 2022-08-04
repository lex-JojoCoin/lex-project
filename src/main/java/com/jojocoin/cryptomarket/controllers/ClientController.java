package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;
import com.jojocoin.cryptomarket.dtos.response.CardResponseDto;
import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.dtos.response.MainWalletResponseDto;
import com.jojocoin.cryptomarket.models.CardModel;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ClientModel>> findAll() {
        List<ClientModel> all = service.findAll();
        log.info("All clients found");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientModel> findById(@PathVariable UUID id){
        ClientModel byId = service.findById(id);
        log.info("Client found from id: " + id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @GetMapping("/findByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> findByCpf(@PathVariable String cpf){
        ClientModel byCpf = service.findByCpf(cpf);
        log.info("Client found from id: " + cpf);
        return new ResponseEntity<>(new ClientResponseDto(byCpf), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody @Valid ClientRequestDto request){
        ClientModel save = service.save(request);
        log.info("New client saved on database");
        return new ResponseEntity<>(new ClientResponseDto(save), HttpStatus.CREATED);
    }
    @PutMapping("/updateById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientModel> update(@PathVariable UUID id,
                                              @RequestBody @Valid ClientRequestDto request){
        ClientModel update = service.update(id, request);
        log.info("Client updated on database by id: " + id);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @PutMapping("/updateByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> update(@PathVariable String cpf,
                                                    @RequestBody @Valid ClientRequestDto request){
        ClientModel update = service.update(cpf, request);
        log.info("Client updated on database by id: " + cpf);
        return new ResponseEntity<>(new ClientResponseDto(update), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        service.delete(id);
        log.info("Client deleted from database by id");
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @DeleteMapping("/deleteByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> delete(@PathVariable String cpf){
        service.delete(cpf);
        log.info("Client deleted from database by cpf");
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @PatchMapping("/{cpf}/cards/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> addCreditCard(@PathVariable String cpf,
                                                           @RequestBody @Valid CardRequestDto request){
        ClientModel patch = service.addCreditCard(cpf, request);
        log.info("Added credit card from client with cpf: " + cpf);
        return new ResponseEntity<>(new ClientResponseDto(patch), HttpStatus.OK);

    }

    @GetMapping("/{cpf}/cryptos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MainWalletResponseDto> findAllCryptos(@PathVariable String cpf){
        MainWalletModel mainWallet = service.findByCpf(cpf).getMainWallet();
        log.info("Find all wallets from client with cpf: " + cpf);
        return new ResponseEntity<>(new MainWalletResponseDto(mainWallet), HttpStatus.OK);
    }

    @PatchMapping("/{cpf}/cryptos/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> createCryptoWallet(@PathVariable String cpf,
                                                                @RequestBody @Valid CryptoWalletRequestDto request){
        ClientModel patch = service.createCryptoWallet(cpf, request);
        log.info("Added crypto wallet from client with cpf: " + cpf);
        return new ResponseEntity<>(new ClientResponseDto(patch), HttpStatus.OK);

    }
}
