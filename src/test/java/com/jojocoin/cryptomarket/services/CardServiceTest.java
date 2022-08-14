package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.CardModel;
import com.jojocoin.cryptomarket.repository.CardRepository;
import com.jojocoin.cryptomarket.services.interfaces.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceTest {

    private CardService service;
    private CardRepository repository;
    private CardRequestDto pojoRequest;
    private CardModel pojoModel;

    @BeforeEach
    void setUp(){
        this.repository = mock(CardRepository.class);
        this.service = new CardServiceImpl(repository);
        this.pojoRequest = new CardRequestDto("Visa", "4716 0370 8535 2945",
                "test", "409", "03/2023");
        this.pojoModel = new CardModel(1L, "Visa", "4716 0370 8535 2945",
                "test", "409", "03/2023");
    }

    @Test
    @DisplayName("Should return a valid card when the given number is valid")
    void ShouldReturnValidCardWhenTheGivenNumberIsValid(){
        String pojoNumber = pojoRequest.getNumber();
        when(repository.findByNumber(pojoNumber)).thenReturn(Optional.of(pojoModel));

        CardModel result = service.findByNumber(pojoNumber);

        assertEquals(result, pojoModel);
        assertEquals(result.getId(), pojoModel.getId());
        assertEquals(result.getNumber(), pojoModel.getNumber());
        assertEquals(result.getName(), pojoModel.getName());
        assertEquals(result.getCvv(), pojoModel.getCvv());
        assertEquals(result.getExpiration(), pojoModel.getExpiration());

        verify(repository, times(1)).findByNumber(pojoNumber);
    }

    @Test
    @DisplayName("Should return a exception when the given number is random")
    void ShouldExceptionWhenTheGivenNumberIsRandom(){
        String number = "a1a1 b2b2 c3c3 d4d4";
        when(repository.findByNumber(number)).thenThrow(new ResourceNotFoundException(number));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.findByNumber(number));

        assertEquals(ResourceNotFoundException.class, exception.getClass());
        verify(repository,times(1)).findByNumber(number);
    }

    @Test
    @DisplayName("Should return a valid card when the given data is valid")
    void ShouldReturnValidCardWhenTheGivenDataIsValid(){
        when(repository.save(any())).thenReturn(pojoModel);

        CardModel result = service.save(pojoRequest);

        assertEquals(result, pojoModel);
        assertEquals(result.getId(), pojoModel.getId());
        assertEquals(result.getNumber(), pojoModel.getNumber());
        assertEquals(result.getName(), pojoModel.getName());
        assertEquals(result.getCvv(), pojoModel.getCvv());
        assertEquals(result.getExpiration(), pojoModel.getExpiration());

        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return a valid card when the given number and data is valid")
    void ShouldReturnValidCardWhenTheGivenNumberAndDataIsValid(){
        String cardNumber = pojoRequest.getNumber();
        CardModel updateCard = new CardModel(1L, "MasterCard", "4716 0370 8535 2945",
                "tested", "431", "03/2023");
        when(repository.findByNumber(cardNumber)).thenReturn(Optional.of(pojoModel));
        when(repository.save(any())).thenReturn(updateCard);

        CardModel result = service.update(cardNumber, pojoRequest);

        assertEquals(result, updateCard);

        assertNotEquals(result, pojoModel);

        verify(repository, times(1)).findByNumber(cardNumber);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return nothing when the given number is valid")
    void ShouldReturnNothingFromDeleteMethodWhenTheGivenNumberIsValid(){
        Long cardId = pojoModel.getId();
        String cardNumber = pojoRequest.getNumber();
        when(repository.findByNumber(cardNumber)).thenReturn(Optional.of(pojoModel));
        doNothing().when(repository).deleteById(cardId);

        try {
            service.delete(cardNumber);
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }

        verify(repository, times(1)).deleteById(cardId);
        verify(repository, times(1)).findByNumber(cardNumber);
    }
}
