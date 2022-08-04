package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.UserModelRequestDto;
import com.jojocoin.cryptomarket.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserModel save(UserModelRequestDto entity);
    UserModel update(UUID uuid, UserModelRequestDto entity);
    void deleteById(UUID uuid);
    UserModel findById(UUID uuid);
    List<UserModel> findAll();
}
