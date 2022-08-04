package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.repository.MainWalletRepository;
import com.jojocoin.cryptomarket.services.interfaces.MainWalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MainWalletServiceTest {

    private MainWalletService service;
    private MainWalletRepository repository;
    private MainWalletRequestDto pojoRequest;
    private MainWalletModel pojoModel;

    @BeforeEach
    void setUp(){
        this.repository = mock(MainWalletRepository.class);
        this.service = new MainWalletServiceImpl(repository);
        this.pojoRequest = new MainWalletRequestDto(new ArrayList<>(), BigDecimal.ZERO);
        this.pojoModel = new MainWalletModel(1L, BigDecimal.ZERO, new ArrayList<>());
    }

    @Test
    @DisplayName("Should return a valid wallet when the given id is valid")
    void ShouldReturnValidWalletWhenTheGivenIdIsValid(){
        when(repository.findById(pojoModel.getId())).thenReturn(Optional.of(pojoModel));

        MainWalletModel result = service.findById(pojoModel.getId());

        assertEquals(result, pojoModel);
        assertEquals(result.getId(), pojoModel.getId());
        assertEquals(result.getBalance(), pojoModel.getBalance());
        assertEquals(result.getCryptoWalletModels(), pojoModel.getCryptoWalletModels());

        verify(repository,times(1)).findById(pojoModel.getId());
    }

    @Test
    @DisplayName("Should return a valid wallet when the given data is valid")
    void ShouldReturnValidWalletTheTheGivenDataIsValid(){
        when(repository.save(any())).thenReturn(pojoModel);

        MainWalletModel result = service.save(pojoRequest);

        assertEquals(result, pojoModel);
        assertEquals(result.getId(), pojoModel.getId());
        assertEquals(result.getBalance(), pojoModel.getBalance());
        assertEquals(result.getCryptoWalletModels(), pojoModel.getCryptoWalletModels());

        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return a updated wallet when the given id and data is valid")
    void ShouldReturnUpdatedWalletWhenTheGivenDataIsValid(){
        MainWalletModel updatedWallet = new MainWalletModel(1L, BigDecimal.valueOf(250), new ArrayList<>());
        when(repository.findById(pojoModel.getId())).thenReturn(Optional.of(pojoModel));
        when(repository.save(any())).thenReturn(updatedWallet);

        MainWalletModel result = service.update(pojoModel.getId(), pojoRequest);

        assertEquals(result, updatedWallet);
        assertNotEquals(result, pojoModel);

        verify(repository, times(1)).findById(pojoModel.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return nothing when the given number is valid")
    void ShouldReturnNothingFromDeleteMethodWhenTheGivenNumberIsValid(){
        when(repository.findById(pojoModel.getId())).thenReturn(Optional.of(pojoModel));
        doNothing().when(repository).delete(any());

        try {
            service.deleteById(pojoModel.getId());
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }

        verify(repository, times(1)).findById(pojoModel.getId());
        verify(repository, times(1)).delete(any());
    }

}
