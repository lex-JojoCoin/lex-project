package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.MainWalletRequestDto;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.MainWalletModel;
import com.jojocoin.cryptomarket.repository.MainWalletRepository;
import com.jojocoin.cryptomarket.services.interfaces.MainWalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class MainWalletServiceImpl implements MainWalletService {

    private final MainWalletRepository mainWalletRepository;

    @Override
    public List<MainWalletModel> findAll() {
        return mainWalletRepository.findAll();
    }

    @Override
    public MainWalletModel findById(Long id) {
        return mainWalletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public MainWalletModel save(MainWalletRequestDto request) {
        MainWalletModel mainWalletModel = new MainWalletModel(
                null,
                request.getBalance(),
                null
        );

        return mainWalletRepository.save(mainWalletModel);
    }

    @Override
    public MainWalletModel update(Long id, MainWalletRequestDto request) {
        MainWalletModel mainWalletModel = findById(id);
        BeanUtils.copyProperties(request, mainWalletModel);
        return mainWalletRepository.save(mainWalletModel);
    }

    public void deleteById(Long id) {
        MainWalletModel wallet = findById(id);
        mainWalletRepository.delete(wallet);
    }

}
