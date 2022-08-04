package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.dtos.request.UserRequestDto;
import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.models.RoleModel;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.services.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;
    private UserService userService;
    private MainWalletService walletService;
    private CardService cardService;
    private CryptoWalletService cryptoWalletService;

    private UserRequestDto pojoUserRequest;
    private UserModel pojoUserModel;
    private MainWalletRequestDto pojoWalletRequest;
    private MainWalletModel pojoWalletModel;
    private ClientRequestDto pojoClientRequest;
    private ClientModel pojoClientModel;

    @BeforeEach
    void setUp(){
        this.clientRepository = mock(ClientRepository.class);
        this.userService = mock(UserService.class);
        this.walletService = mock(MainWalletService.class);
        this.cardService = mock(CardService.class);
        this.cryptoWalletService = mock(CryptoWalletService.class);
        this.clientService = new ClientServiceImpl(clientRepository, userService, walletService, cardService, cryptoWalletService);
        createPojo();
    }

    private void createPojo(){
        this.pojoUserRequest = new UserRequestDto("test", "test123");
        this.pojoUserModel = new UserModel(UUID.randomUUID(), "test", "test123", Set.of(new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER)));

        this.pojoWalletRequest = new MainWalletRequestDto(BigDecimal.ZERO);
        this.pojoWalletModel = new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>());

        this.pojoClientRequest = new ClientRequestDto("Test", "995.195.240-29", "test", "test123");
        this.pojoClientModel = new ClientModel(UUID.randomUUID(), "test", "995.195.240-29", "1a2d3f4g5h6j7k89", new ArrayList<>(), new UserModel(UUID.randomUUID(), "test", "test123", Set.of(new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER))), new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>()));
    }

    @Test
    @DisplayName("Should return valid user when the given data is valid")
    void ShouldReturnValidUserWhenTheGivenDataIsValid(){
        when(userService.save(any())).thenReturn(pojoUserModel);
        when(walletService.save(any())).thenReturn(pojoWalletModel);
        when(clientRepository.save(any())).thenReturn(pojoClientModel);

        ClientModel result = clientService.save(pojoClientRequest);

        assertEquals(result, pojoClientModel);
        assertEquals(result.getClientId(), pojoClientModel.getClientId());
        assertEquals(result.getName(), pojoClientModel.getName());
        assertEquals(result.getCpf(), pojoClientModel.getCpf());
        assertEquals(result.getPix(), pojoClientModel.getPix());
        assertEquals(result.getCards(), pojoClientModel.getCards());
        assertEquals(result.getUser(), pojoClientModel.getUser());
        assertEquals(result.getMainWallet(), pojoClientModel.getMainWallet());

        verify(userService,times(1)).save(any());
        verify(walletService,times(1)).save(any());
        verify(clientRepository,times(1)).save(any());
    }
}
