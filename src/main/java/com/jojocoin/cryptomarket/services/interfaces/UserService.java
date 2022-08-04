package com.jojocoin.cryptomarket.services.interfaces;

import com.jojocoin.cryptomarket.dtos.request.UserRequestDto;
import com.jojocoin.cryptomarket.models.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserModel save(UserRequestDto entity);
    UserModel update(UUID uuid, UserRequestDto entity);
    void deleteById(UUID uuid);
    UserModel findById(UUID uuid);
    UserModel findByUsername(String username);
    List<UserModel> findAll();
}
