package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.*;
import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.*;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.services.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;
    private UserService userService;
    private MainWalletService walletService;
    private CardService cardService;
    private CryptoWalletService cryptoWalletService;

    private UserModel pojoUserModel;
    private MainWalletModel pojoWalletModel;
    private ClientRequestDto pojoClientRequest;
    private ClientModel pojoClientModel;
    private CardRequestDto pojoCardRequest;
    private CardModel pojoCardModel;
    private CryptoWalletRequestDto pojoCryptoWalletRequest;
    private CryptoWalletModel pojoCryptoWalletModel;


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
        UserRequestDto pojoUserRequest = new UserRequestDto("test", "test123");
        this.pojoUserModel = new UserModel(UUID.randomUUID(), "test", "test123", Set.of(new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER)));

        MainWalletRequestDto pojoWalletRequest = new MainWalletRequestDto(new ArrayList<>(), BigDecimal.ZERO);
        this.pojoWalletModel = new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>());

        this.pojoClientRequest = new ClientRequestDto("Test", "995.195.240-29", "test", "test123");
        this.pojoClientModel = new ClientModel(UUID.randomUUID(), "test", "995.195.240-29", "1a2d3f4g5h6j7k89", new ArrayList<>(), new UserModel(UUID.randomUUID(), "test", "test123", Set.of(new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER))), new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>()));

        this.pojoCardRequest = new CardRequestDto("Visa", "4716 0370 8535 2945", "test", "409", "03/2023");
        this.pojoCardModel = new CardModel(1L, "Visa", "4716 0370 8535 2945", "test", "409", "03/2023");

        this.pojoCryptoWalletRequest = new CryptoWalletRequestDto(BigDecimal.TEN, BigDecimal.valueOf(200),"bitcoin");
        this.pojoCryptoWalletModel = new CryptoWalletModel(1L, BigDecimal.TEN, BigDecimal.valueOf(200), new CoinModel("lexcoin", "LCN", "Lex Coin", BigDecimal.valueOf(0.53)));
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

    @Test
    @DisplayName("Should return valid user when the given id is valid")
    void ShouldReturnValidUserWhenTheGivenIdIsValid(){
        UUID clientId = pojoClientModel.getClientId();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(pojoClientModel));

        ClientModel result = clientService.findById(clientId);

        assertEquals(result, pojoClientModel);
        assertEquals(result.getClientId(), pojoClientModel.getClientId());
        assertEquals(result.getName(), pojoClientModel.getName());
        assertEquals(result.getCpf(), pojoClientModel.getCpf());
        assertEquals(result.getPix(), pojoClientModel.getPix());
        assertEquals(result.getCards(), pojoClientModel.getCards());
        assertEquals(result.getUser(), pojoClientModel.getUser());
        assertEquals(result.getMainWallet(), pojoClientModel.getMainWallet());

        verify(clientRepository,times(1)).findById(clientId);
    }

    @Test
    @DisplayName("Should return resource not found exception when the given id is random")
    void ShouldReturnResourceNotFoundExceptionWhenTheGivenIdIsRandom(){
        UUID random = UUID.randomUUID();
        when(clientRepository.findById(random)).thenThrow(new ResourceNotFoundException(random));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> clientService.findById(random));

        assertEquals(exception.getClass(), ResourceNotFoundException.class);
        assertEquals("Resource not found for the given id: " + random, exception.getMessage());
        verify(clientRepository, times(1)).findById(random);
    }

    @Test
    @DisplayName("Should return valid user when the given cpf is valid")
    void ShouldReturnValidUserWhenTheGivenCpfIsValid(){
        String cpf = pojoClientModel.getCpf();
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.of(pojoClientModel));

        ClientModel result = clientService.findByCpf(cpf);

        assertEquals(result, pojoClientModel);
        assertEquals(result.getClientId(), pojoClientModel.getClientId());
        assertEquals(result.getName(), pojoClientModel.getName());
        assertEquals(result.getCpf(), pojoClientModel.getCpf());
        assertEquals(result.getPix(), pojoClientModel.getPix());
        assertEquals(result.getCards(), pojoClientModel.getCards());
        assertEquals(result.getUser(), pojoClientModel.getUser());
        assertEquals(result.getMainWallet(), pojoClientModel.getMainWallet());

        verify(clientRepository,times(1)).findByCpf(cpf);
    }

    @Test
    @DisplayName("Should return resource not found exception when the given cpf is random")
    void ShouldReturnResourceNotFoundExceptionWhenTheGivenCpfIsRandom(){
        String randomCpf = "000.000.000-00";

        when(clientRepository.findByCpf(randomCpf)).thenThrow(new ResourceNotFoundException(randomCpf));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> clientService.findByCpf(randomCpf));

        assertEquals(exception.getClass(), ResourceNotFoundException.class);
        assertEquals("Resource not found for the given: " + randomCpf, exception.getMessage());
        verify(clientRepository, times(1)).findByCpf(randomCpf);
    }

    @Test
    @DisplayName("Should return updated user when the given id and data is valid")
    void ShouldReturnUpdatedUserWhenTheGivenIdAndDataIsValid(){
        UUID clientId = pojoClientModel.getClientId();
        ClientRequestDto updateRequest = new ClientRequestDto("tested", "398.561.560-84", "tested", "tested123");
        ClientModel updatedClient = new ClientModel(UUID.randomUUID(), "tested", "398.561.560-84", "1a2d3f4g5h3j7k88",
                new ArrayList<>(),
                new UserModel(UUID.randomUUID(), "tested", "tested123",
                        Set.of(new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER))),
                new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>()));
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(pojoClientModel));
        when(clientRepository.save(any())).thenReturn(updatedClient);

        ClientModel result = clientService.update(clientId, updateRequest);

        assertEquals(result, updatedClient);
        assertNotEquals(result, pojoClientModel);

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Should return updated user when the given cpf and data is valid")
    void ShouldReturnUpdatedUserWhenTheGivenCpfAndDataIsValid(){
        String clientCpf = pojoClientModel.getCpf();
        ClientRequestDto updateRequest = new ClientRequestDto("tested", "398.561.560-84", "tested", "tested123");
        ClientModel updatedClient = new ClientModel(UUID.randomUUID(), "tested", "398.561.560-84", "1a2d3f4g5h3j7k88",
                new ArrayList<>(),
                new UserModel(UUID.randomUUID(), "tested", "tested123",
                        Set.of(new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER))),
                new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>()));
        when(clientRepository.findByCpf(clientCpf)).thenReturn(Optional.of(pojoClientModel));
        when(clientRepository.save(any())).thenReturn(updatedClient);

        ClientModel result = clientService.update(clientCpf, updateRequest);

        assertEquals(result, updatedClient);
        assertNotEquals(result, pojoClientModel);

        verify(clientRepository, times(1)).findByCpf(clientCpf);
        verify(clientRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return nothing from delete method when the given id is valid")
    void ShouldReturnNothingFromDeleteMethodWhenTheGivenIdIsValid(){
        UUID clientId = pojoClientModel.getClientId();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(pojoClientModel));
        doNothing().when(clientRepository).deleteById(clientId);

        try {
            clientService.delete(clientId);
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }

        verify(clientRepository, times(1)).findById(clientId);
        verify(clientRepository, times(1)).deleteById(clientId);
    }

    @Test
    @DisplayName("Should return nothing from delete method when the given cpf is valid")
    void ShouldReturnNothingFromDeleteMethodWhenTheGivenCpfIsValid(){
        String clientCpf = pojoClientModel.getCpf();
        when(clientRepository.findByCpf(clientCpf)).thenReturn(Optional.of(pojoClientModel));
        doNothing().when(clientRepository).deleteById(pojoClientModel.getClientId());

        try {
            clientService.delete(clientCpf);
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }

        verify(clientRepository, times(1)).findByCpf(clientCpf);
        verify(clientRepository, times(1)).deleteById(pojoClientModel.getClientId());
    }

    @Test
    @DisplayName("Should return client with a valid card when the given cpf and data is valid")
    void ShouldReturnClientWithValidCardWhenTheGivenCpfAndDataIsValid(){
        String clientCpf = pojoClientModel.getCpf();

        when(cardService.save(any())).thenReturn(pojoCardModel);
        when(clientRepository.findByCpf(clientCpf)).thenReturn(Optional.of(pojoClientModel));
        when(clientRepository.save(any())).thenReturn(pojoClientModel);

        ClientModel result = clientService.addCreditCard(clientCpf, pojoCardRequest);

        assertEquals(result.getClientId(), pojoClientModel.getClientId());
        assertEquals(result.getCpf(), pojoClientModel.getCpf());
        assertEquals(result.getCards(), pojoClientModel.getCards());

        verify(cardService, times(1)).save(any());
        verify(clientRepository, times(1)).save(any());
        verify(clientRepository, times(1)).findByCpf(clientCpf);
    }

    @Test
    @DisplayName("Should return client with a valid crypto wallet when the given cpf and data is valid")
    void ShouldReturnClientWithValidCryptoWalletWhenTheGivenCpfAndDataIsValid(){
        String clientCpf = pojoClientModel.getCpf();

        when(cryptoWalletService.save(any())).thenReturn(pojoCryptoWalletModel);
        when(clientRepository.findByCpf(clientCpf)).thenReturn(Optional.of(pojoClientModel));
        when(clientRepository.save(any())).thenReturn(pojoClientModel);

        ClientModel result = clientService.createCryptoWallet(clientCpf, pojoCryptoWalletRequest);

        assertEquals(result.getClientId(), pojoClientModel.getClientId());
        assertEquals(result.getCpf(), pojoClientModel.getCpf());
        assertEquals(result.getMainWallet(), pojoClientModel.getMainWallet());

        verify(cryptoWalletService, times(1)).save(any());
        verify(clientRepository, times(1)).save(any());
        verify(clientRepository, times(1)).findByCpf(clientCpf);
    }
}
