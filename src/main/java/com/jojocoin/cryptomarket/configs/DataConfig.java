package com.jojocoin.cryptomarket.configs;

import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.models.RoleModel;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.repository.RoleRepository;
import com.jojocoin.cryptomarket.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
@Profile("test")
@AllArgsConstructor
public class DataConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        RoleModel roleAdmin = new RoleModel(UUID.randomUUID(), RoleName.ROLE_ADMIN);
        roleRepository.save(roleAdmin);

        RoleModel roleUser = new RoleModel(UUID.randomUUID(), RoleName.ROLE_USER);
        roleRepository.save(roleUser);

        Set<RoleModel> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN));

        UserModel user = new UserModel(
                UUID.randomUUID(),
                "admin",
                passwordEncoder.encode("senha123"),
                roles);
        userRepository.save(user);
    }
}
