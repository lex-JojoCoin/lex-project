package com.jojocoin.cryptomarket.controllers;

import com.jojocoin.cryptomarket.dtos.request.UserModelRequestDto;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserModel>> findAll(){
        List<UserModel> models = service.findAll();
        log.info("All users founded");
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserModel> findById(@PathVariable UUID uuid){
        UserModel model = service.findById(uuid);
        log.info("User found from the given id");
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserModel> save(@RequestBody @Valid UserModelRequestDto request){
        UserModel model = service.save(request);
        log.info("New user created");
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable UUID uuid){
        service.deleteById(uuid);
        log.info("Deleted user");
        return new ResponseEntity<>("Ok!", HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserModel> update(@PathVariable UUID uuid,
                                            UserModelRequestDto request){
        UserModel model = service.update(uuid, request);
        log.info("User updated");
        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
