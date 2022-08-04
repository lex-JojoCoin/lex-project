package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.repository.CryptoWalletRepository;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import com.jojocoin.cryptomarket.services.interfaces.CryptoWalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CryptoWalletServiceTest {

    private CryptoWalletService cryptoService;
    private CoinService coinService;
    private CryptoWalletRepository cryptoRepository;
    private CryptoWalletRequestDto pojoCryptoRequest;
    private CryptoWalletModel pojoCryptoModel;
    private CoinModel pojoCoinModel;

    @BeforeEach
    void setUp(){
        this.cryptoRepository = mock(CryptoWalletRepository.class);
        this.coinService = mock(CoinService.class);
        this.cryptoService = new CryptoWalletServiceImpl(cryptoRepository, coinService);

        this.pojoCoinModel = new CoinModel("lexcoin", "LCN", "Lex Coin", BigDecimal.valueOf(0.53));

        this.pojoCryptoRequest = new CryptoWalletRequestDto(BigDecimal.TEN, BigDecimal.valueOf(200),"bitcoin");
        this.pojoCryptoModel = new CryptoWalletModel(1L, BigDecimal.TEN, BigDecimal.valueOf(200), pojoCoinModel);
    }

    @Test
    @DisplayName("Should return a valid crypto wallet when the given id is valid")
    void ShouldReturnValidWalletWhenTheGivenIdIsValid(){
        when(cryptoRepository.findById(pojoCryptoModel.getId())).thenReturn(Optional.of(pojoCryptoModel));

        CryptoWalletModel result = cryptoService.findById(pojoCryptoModel.getId());

        assertEquals(result, pojoCryptoModel);
        assertEquals(result.getId(), pojoCryptoModel.getId());
        assertEquals(result.getAmount(), pojoCryptoModel.getAmount());
        assertEquals(result.getBalance(), pojoCryptoModel.getBalance());
        assertEquals(result.getCoin(), pojoCryptoModel.getCoin());

        verify(cryptoRepository,times(1)).findById(pojoCryptoModel.getId());
    }

    @Test
    @DisplayName("Should return a valid crypto wallet when the given data is valid")
    void ShouldReturnValidWalletTheTheGivenDataIsValid(){
        when(cryptoRepository.save(any())).thenReturn(pojoCryptoModel);
        when(coinService.findById(pojoCoinModel.getName())).thenReturn(pojoCoinModel);
        CryptoWalletModel result = cryptoService.save(pojoCryptoRequest);

        assertEquals(result, pojoCryptoModel);
        assertEquals(result.getId(), pojoCryptoModel.getId());
        assertEquals(result.getAmount(), pojoCryptoModel.getAmount());
        assertEquals(result.getBalance(), pojoCryptoModel.getBalance());
        assertEquals(result.getCoin(), pojoCryptoModel.getCoin());

        verify(cryptoRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Should return a updated crypto wallet when the given id and data is valid")
    void ShouldReturnUpdatedWalletWhenTheGivenDataIsValid(){
        CryptoWalletModel updateCrypto = new CryptoWalletModel(1L, BigDecimal.valueOf(300), BigDecimal.valueOf(500), pojoCoinModel);
        when(cryptoRepository.findById(pojoCryptoModel.getId())).thenReturn(Optional.of(pojoCryptoModel));
        when(cryptoRepository.save(any())).thenReturn(updateCrypto);

        CryptoWalletModel result = cryptoService.update(pojoCryptoModel.getId(), pojoCryptoRequest);

        assertEquals(result, updateCrypto);
        assertNotEquals(result, pojoCryptoModel);

        verify(cryptoRepository,times(1)).findById(pojoCryptoModel.getId());
        verify(cryptoRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("Should return nothing when the given number is valid")
    void ShouldReturnNothingFromDeleteMethodWhenTheGivenNumberIsValid(){
        when(cryptoRepository.findById(pojoCryptoModel.getId())).thenReturn(Optional.of(pojoCryptoModel));
        doNothing().when(cryptoRepository).deleteById(pojoCryptoModel.getId());

        try{
            cryptoService.delete(pojoCryptoModel.getId());
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }

        verify(cryptoRepository, times(1)).findById(pojoCryptoModel.getId());
        verify(cryptoRepository, times(1)).deleteById(pojoCryptoModel.getId());
    }


}
