package com.jojocoin.cryptomarket.configs;

import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.feign.CoinClientFeignService;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.RoleModel;
import com.jojocoin.cryptomarket.models.UserModel;
import com.jojocoin.cryptomarket.repository.CoinRepository;
import com.jojocoin.cryptomarket.repository.RoleRepository;
import com.jojocoin.cryptomarket.repository.UserRepository;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
@Profile("test")
@AllArgsConstructor
public class DataConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CoinRepository coinRepository;
    private PasswordEncoder passwordEncoder;
    private CoinClientFeignService feignService;


    @Override
    public void run(String... args) throws Exception {
        loadCoinsValues();
        loadAdminUser();
    }

    private void loadAdminUser() {
        if (userRepository.findAll().isEmpty()) {
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

    private void loadCoinsValues(){
        List<CoinModel> coinData = feignService.getAll().getData();
        coinRepository.saveAll(coinData);
    }
}
