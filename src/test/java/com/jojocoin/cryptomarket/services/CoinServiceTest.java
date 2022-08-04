package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.repository.CoinRepository;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CoinServiceTest {

    private CoinService service;
    private CoinRepository repository;

    @BeforeEach
    void setUp(){
        this.repository = mock(CoinRepository.class);
        this.service = new CoinServiceImpl(repository);
    }

    @Test
    @DisplayName("Should return coin when the given name is valid")
    void ShouldReturnCoinWhenTheGivenNameIsValid(){
        CoinModel pojoCoinModel = new CoinModel("lexcoin", "LCN", "Lex Coin", BigDecimal.valueOf(0.53));
        String name = pojoCoinModel.getName();
        when(repository.findById(name)).thenReturn(Optional.of(pojoCoinModel));

        CoinModel result = service.findById(name);

        assertEquals(result, pojoCoinModel);
        assertEquals(result.getId(), pojoCoinModel.getId());
        assertEquals(result.getSymbol(), pojoCoinModel.getSymbol());
        assertEquals(result.getName(), pojoCoinModel.getName());
        assertEquals(result.getPriceUsd(), pojoCoinModel.getPriceUsd());

        verify(repository, times(1)).findById(name);
    }

    @Test
    @DisplayName("Should return resource not found exception when the given name is invalid")
    void ShouldReturnExceptionWhenTheGivenNameIsInvalid(){
        String name = "1";
        when(repository.findById(name)).thenThrow(new ResourceNotFoundException(name));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.findById(name));

        assertEquals(exception.getClass(), ResourceNotFoundException.class);
        verify(repository, times(1)).findById(name);
    }
}
