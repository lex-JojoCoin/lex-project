package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.UserRequestDto;
import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.exceptions.DataConflictException;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.RoleModel;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.repository.RoleRepository;
import com.jojocoin.cryptomarket.repository.UserRepository;
import com.jojocoin.cryptomarket.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
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

    public UserModel save(UserRequestDto entity){
        verifyIfTheUsernameAlreadyExists(entity.getUsername());
        Set<RoleModel> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER));

        UserModel user = new UserModel(null,
                entity.getUsername(),
                passwordEncoder.encode(entity.getPassword()),
                roles);
        return userRepository.save(user);
    }

    public UserModel update(UUID uuid, UserRequestDto entity){
        verifyIfTheUsernameAlreadyExists(entity.getUsername());
        UserModel byId = findById(uuid);
        BeanUtils.copyProperties(entity, byId);
        return userRepository.save(byId);
    }

    public void deleteById(UUID uuid){
        UserModel user = findById(uuid);
        try {
            userRepository.deleteById(user.getUserId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }

    }

    public UserModel findById(UUID uuid){
       return userRepository.findById(uuid).orElseThrow(()-> new ResourceNotFoundException(uuid));
    }

    public UserModel findByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException(username));
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    private void verifyIfTheUsernameAlreadyExists(String username){
        if(userRepository.findByUsername(username).isPresent()){
            throw new DataConflictException("The username already exists!");
        }
    }
}
