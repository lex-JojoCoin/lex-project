package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;

import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.repository.CryptoWalletRepository;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import com.jojocoin.cryptomarket.services.interfaces.CryptoWalletService;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CryptoWalletServiceImpl implements CryptoWalletService {
    private final CryptoWalletRepository repository;
    private final CoinService coinService;

    public List<CryptoWalletModel> findAll(){
        return repository.findAll();
    }

    public CryptoWalletModel findById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public List<CryptoWalletModel> saveAll(List<CryptoWalletModel> all){
        return repository.saveAll(all);
    }

    public CryptoWalletModel save(CryptoWalletRequestDto request){
        CoinModel coin = coinService.findById(request.getCoinName());
        CryptoWalletModel model =
                new CryptoWalletModel(null, request.getAmount(),
                        request.getPrice(), coin);
        return repository.save(model);
    }

    public CryptoWalletModel update(Long id, CryptoWalletRequestDto request){
        CryptoWalletModel byId = findById(id);
        BeanUtils.copyProperties(request, byId);
        return repository.save(byId);
    }

    public void updateBalance(){
        List<CryptoWalletModel> all = findAll();
        all.forEach(CryptoWalletModel::updateBalance);
        repository.saveAll(all);
    }

    public CryptoWalletModel add(Long id, CryptoWalletRequestDto request){
        CryptoWalletModel model = findById(id);
        model.addAmount(request.getAmount());
        return repository.save(model);
    }

    public CryptoWalletModel subtract(Long id, CryptoWalletRequestDto request){
        CryptoWalletModel model = findById(id);
        model.subAmount(request.getAmount());
        return repository.save(model);
    }

    public void delete(Long id){
        CryptoWalletModel byId = findById(id);
        try {
            repository.deleteById(byId.getId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }
    }
}
