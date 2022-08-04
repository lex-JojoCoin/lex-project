package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;

import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.repository.CryptoWalletRepository;
import com.jojocoin.cryptomarket.repository.MainWalletRepository;
import com.jojocoin.cryptomarket.services.interfaces.CryptoWalletService;
import com.jojocoin.cryptomarket.services.interfaces.MainWalletService;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class CryptoWalletServiceImpl implements CryptoWalletService {


    private final CryptoWalletRepository cryptoWalletRepository;

    @Override
    public CryptoWalletModel findById(Long id) {
        return cryptoWalletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public CryptoWalletModel save(CryptoWalletRequestDto request) {
        CryptoWalletModel cryptoWallet = new CryptoWalletModel();
        BeanUtils.copyProperties(request, cryptoWallet);

        return cryptoWalletRepository.save(cryptoWallet);
    }

    @Override
    public CryptoWalletModel update(Long id, CryptoWalletRequestDto request) {

        CryptoWalletModel cryptoWallet = findById(id);
        BeanUtils.copyProperties(request, cryptoWallet);

        return cryptoWalletRepository.save(cryptoWallet);
    }

    @Override
    public void delete(Long id) {

        CryptoWalletModel cryptoWallet = findById(id);
        try {
            cryptoWalletRepository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }

    private final CryptoWalletRepository repository;

    private final CoinService coinService;

    public List<CryptoWalletModel> findAll(){
        return repository.findAll();
    }

    public CryptoWalletModel findById(Long id){
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public CryptoWalletModel save(CryptoWalletRequestDto request){
        CoinModel coin = coinService.findById(request.getCoinName());
        CryptoWalletModel model =
                new CryptoWalletModel(
                        null,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        coin
                );
        return repository.save(model);
    }

    public CryptoWalletModel update(Long id, CryptoWalletRequestDto request){
        CryptoWalletModel byId = findById(id);
        BeanUtils.copyProperties(request, byId);
        return repository.save(byId);
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
