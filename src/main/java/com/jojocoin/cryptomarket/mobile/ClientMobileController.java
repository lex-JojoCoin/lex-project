package com.jojocoin.cryptomarket.mobile;

import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.dtos.response.MainWalletResponseDto;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/mobile/clients")
public class ClientMobileController {

    private final ClientService service;

    @GetMapping("/mobile/findByCpf/{cpf}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ClientResponseDto> findByCpf(@PathVariable String cpf){
        ClientModel byCpf = service.findByCpf(cpf);
        log.info("Client found from id: " + cpf);
        return new ResponseEntity<>(new ClientResponseDto(byCpf), HttpStatus.OK);
    }


    @GetMapping("/mobile/{cpf}/cryptos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MainWalletResponseDto> findAllCryptos(@PathVariable String cpf){
        MainWalletModel mainWallet = service.findByCpf(cpf).getMainWallet();
        log.info("Find all wallets from client with cpf: " + cpf);
        return new ResponseEntity<>(new MainWalletResponseDto(mainWallet), HttpStatus.OK);
    }
}
