package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ClientModel>> findAll() {
        List<ClientModel> all = service.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("findById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientModel> findById(@PathVariable UUID id){
        ClientModel byId = service.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @GetMapping("findByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> findByCpf(@PathVariable String cpf){
        ClientModel byCpf = service.findByCpf(cpf);
        return new ResponseEntity<>(new ClientResponseDto(byCpf), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody ClientRequestDto request){
        ClientModel save = service.save(request);
        return new ResponseEntity<>(new ClientResponseDto(save), HttpStatus.CREATED);
    }
    @PutMapping("findById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientModel> update(@PathVariable UUID id,
                                                    @RequestBody ClientRequestDto request){
        ClientModel update = service.update(id, request);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @PutMapping("findByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> update(@PathVariable String cpf,
                                                    @RequestBody ClientRequestDto request){
        ClientModel update = service.update(cpf, request);
        return new ResponseEntity<>(new ClientResponseDto(update), HttpStatus.OK);
    }

    @DeleteMapping("findById/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        service.delete(id);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @DeleteMapping("findByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<String> delete(@PathVariable String cpf){
        service.delete(cpf);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
