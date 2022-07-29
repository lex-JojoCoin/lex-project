package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.UserModelRequestDto;
import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.RoleModel;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.repository.RoleRepository;
import com.jojocoin.cryptomarket.repository.UserRepository;
import com.jojocoin.cryptomarket.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserModel save(UserModelRequestDto entity){
        Set<RoleModel> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER));

        UserModel user = new UserModel();
        user.setUsername(entity.getUsername());
        user.setPassword(passwordEncoder.encode(entity.getPassword()));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public UserModel update(UUID uuid, UserModelRequestDto entity){
        UserModel byId = findById(uuid);
        BeanUtils.copyProperties(entity, byId);
        return userRepository.save(byId);
    }

    public void deleteById(UUID uuid){
        UserModel user = findById(uuid);
        userRepository.delete(user);
    }

    public UserModel findById(UUID uuid){
       return userRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException(uuid));
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }
}
