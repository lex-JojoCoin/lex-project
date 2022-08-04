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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService service;
    private UserRepository repository;
    private PasswordEncoder encoder;
    private RoleRepository rolesRepository;
    private UserModel expectedUser;

    private RoleModel expectedRole;


    @BeforeEach
    void setUp(){
        this.repository = mock(UserRepository.class);
        this.rolesRepository = mock(RoleRepository.class);
        this.encoder = new BCryptPasswordEncoder();
        this.service = new UserServiceImpl(repository, rolesRepository, encoder);
        this.expectedRole = new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER);
        this.expectedUser = UserModel.builder().userId(UUID.randomUUID())
                .username("Test").password(encoder.encode("test123"))
                .roles(Set.of(expectedRole)).build();
    }

    @Test
    @DisplayName("Should return a valid user when the id is valid")
    void ShouldReturnValidUserWhenIdIsValid(){
        when(repository.findById(expectedUser.getUserId())).thenReturn(Optional.of(expectedUser));

        UserModel result = service.findById(expectedUser.getUserId());
        assertEquals(result.getUserId(), expectedUser.getUserId());
        assertEquals(result.getUsername(), expectedUser.getUsername());
        assertEquals(result.getPassword(), expectedUser.getPassword());
        assertEquals(result.getRoles(), expectedUser.getRoles());

    }

    @Test
    @DisplayName("Should return resource not found exception when id dont exists on database")
    void ShouldReturnResourceNotFoundExceptionWhenIdDontExistsOnDatabase(){
        UUID random = UUID.randomUUID();
        when(repository.findById(random)).thenThrow(new ResourceNotFoundException(random));

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(random));
        assertEquals("Resource not found for the given id: " + random, exception.getMessage());
        verify(repository, times(1)).findById(random);
    }

    @Test
    @DisplayName("Should save a user in database when the given user is valid")
    void ShouldSaveUserWhenTheGivenUserIsValid(){
        UserModel result = null;
        UserRequestDto request = new UserRequestDto(expectedUser.getUsername(), expectedUser.getPassword());

        when(repository.save(any())).thenReturn(expectedUser);
        when(rolesRepository.findByRoleName(expectedRole.getRoleName())).thenReturn(expectedRole);

        try {
            result = service.save(request);
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }

        assertEquals(result.getUserId(), expectedUser.getUserId());
        assertEquals(result.getUsername(), expectedUser.getUsername());
        assertEquals(result.getPassword(), expectedUser.getPassword());
        assertEquals(result.getRoles(), expectedUser.getRoles());
        assertEquals(result.getAuthorities(), expectedUser.getAuthorities());

        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return data conflict exception when the given username already exist")
    void ShouldReturnDataConflictExceptionWhenTheGivenUsernameAlreadyExist(){
        UserRequestDto request = new UserRequestDto(expectedUser.getUsername(), expectedUser.getPassword());
        when(repository.save(any())).thenThrow(new DataConflictException("The username already exists!"));

        DataConflictException exception = assertThrows(DataConflictException.class, () -> service.save(request));
        assertEquals("CONFLICT!: The username already exists!", exception.getMessage());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should return a list of users")
    void ShouldReturnUserList(){
        when(repository.findAll()).thenReturn(List.of(expectedUser));

        List<UserModel> result = service.findAll();
        assertTrue(result.contains(expectedUser));
        assertEquals(result.get(0), expectedUser);
        assertEquals(result.get(0).getUserId(), expectedUser.getUserId());
        assertEquals(result.get(0).getUsername(), expectedUser.getUsername());
        assertEquals(result.get(0).getPassword(), expectedUser.getPassword());
        assertEquals(result.get(0).getRoles(), expectedUser.getRoles());
        assertEquals(result.get(0).getAuthorities(), expectedUser.getAuthorities());
        verify(repository,times(1)).findAll();
    }

    @Test
    @DisplayName("Should return nothing from delete method when the given id is valid")
    void ShouldReturnNothingFromDeleteMethodWhenTheGivenIdIsValid(){
        doNothing().when(repository).deleteById(expectedUser.getUserId());
        when(repository.findById(expectedUser.getUserId())).thenReturn(Optional.of(expectedUser));

        try {
            service.deleteById(expectedUser.getUserId());
        } catch (Exception e){
            fail("Shouldn't throw a exception");
        }
        verify(repository,times(1)).deleteById(expectedUser.getUserId());
    }

    @Test
    @DisplayName("Should return a data conflict exception when the given id isn't valid")
    void ShouldReturnDataIntegrityExceptionWhenTheGivenIdIsNotValid(){
        UUID expectedUserId = expectedUser.getUserId();
        doThrow(new DataIntegrityException()).when(repository).deleteById(expectedUserId);
        when(repository.findById(expectedUserId)).thenReturn(Optional.of(expectedUser));

        DataIntegrityException exception = assertThrows(DataIntegrityException.class, () -> service.deleteById(expectedUserId));
        assertEquals("It's not possible to delete this object, it would violate other data integrity.", exception.getMessage());
        verify(repository,times(1)).deleteById(expectedUserId);
    }

    @Test
    @DisplayName("Should return updated user when the given id and the request is valid")
    void ShouldReturnUpdatedUserWhenTheGivenIdAndTheRequestIsValid(){
        UUID expectedId = expectedUser.getUserId();
        UserRequestDto request = new UserRequestDto("tested", encoder.encode("tested123"));
        UserModel model = new UserModel(UUID.randomUUID(), request.getUsername(), request.getPassword(), expectedUser.getRoles());

        when(repository.findById(expectedId)).thenReturn(Optional.of(expectedUser));
        when(repository.save(any())).thenReturn(model);

        UserModel result = service.update(expectedId, request);

        assertEquals(result, model);
        assertEquals(result.getUserId(), model.getUserId());
        assertEquals(result.getUsername(), model.getUsername());
        assertEquals(result.getPassword(), model.getPassword());
        assertEquals(result.getRoles(), model.getRoles());

        assertNotEquals(result, expectedUser);
        assertNotEquals(result.getUserId(), expectedUser.getUserId());
        assertNotEquals(result.getUsername(), expectedUser.getUsername());
        assertNotEquals(result.getPassword(), expectedUser.getPassword());
        assertEquals(result.getRoles(), expectedUser.getRoles());

        verify(repository,times(1)).findById(expectedId);
        verify(repository,times(1)).save(any());
    }
}
