package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.CardModel;
import com.jojocoin.cryptomarket.repository.CardRepository;
import com.jojocoin.cryptomarket.services.interfaces.CardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository repository;

    public List<CardModel> findAll(){
        return repository.findAll();
    }

    public CardModel findByNumber(String number){
        return repository.findByNumber(number).orElseThrow(()-> new ResourceNotFoundException(number));
    }

    public CardModel save(CardRequestDto request){
        return repository.save(new CardModel(
                null,
                request.getNetwork(),
                request.getNumber(),
                request.getName(),
                request.getCvv(),
                request.getExpiration()
        ));
    }

    public CardModel update(String number, CardRequestDto request){
        CardModel byNumber = findByNumber(number);
        BeanUtils.copyProperties(request, byNumber);
        return repository.save(byNumber);
    }

    public void delete(String number){
        CardModel model = findByNumber(number);
        try {
            repository.deleteById(model.getId());
        } catch (DataIntegrityException ex){
            throw new DataIntegrityException();
        }
    }
}
