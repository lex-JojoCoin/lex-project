package com.jojocoin.cryptomarket.utils.configs;

import com.jojocoin.cryptomarket.dtos.request.CardRequestDto;
import com.jojocoin.cryptomarket.dtos.request.ClientRequestDto;
import com.jojocoin.cryptomarket.dtos.request.CryptoWalletRequestDto;
import com.jojocoin.cryptomarket.enums.RoleName;
import com.jojocoin.cryptomarket.feign.CoinClientFeignService;
import com.jojocoin.cryptomarket.models.*;
import com.jojocoin.cryptomarket.repository.*;
import com.jojocoin.cryptomarket.services.interfaces.BookMarketService;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import com.jojocoin.cryptomarket.services.interfaces.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

@Configuration
@Profile("test")
@AllArgsConstructor
public class DataConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CoinService coinService;
    private ExchangeService exchangeService;
    private BookMarketService bookMarketService;
    private ClientService clientService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        loadCoins();
        loadAdminUser();
        loadClients();
        loadExchange();
    }

//    private void loadCoins(){
//        if (coinService.findAll().isEmpty()){
//           coinService.saveAll();
//        }
//    }

    private void loadAdminUser() {
        if (userRepository.findAll().isEmpty()) {
            RoleModel roleAdmin = new RoleModel(null, RoleName.ROLE_ADMIN);
            roleRepository.save(roleAdmin);

            RoleModel roleUser = new RoleModel(null, RoleName.ROLE_USER);
            roleRepository.save(roleUser);

            Set<RoleModel> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN));

            UserModel user = new UserModel(
                    null,
                    "admin",
                    passwordEncoder.encode("senha123"),
                    roles);
            userRepository.save(user);
        }
    }

    private void loadClients() {

        if (clientService.findAll().isEmpty()) {
            String alexCpf = "011.159.350-62";
            String aliceCpf = "391.540.890-50";

            CardRequestDto alexCard = new CardRequestDto("Master Card",
                    "5182 0484 6328 0824", "Alex Green",
                    "144", "05/2023");

            CardRequestDto aliceCard = new CardRequestDto("Visa", "4024 0071 2320 0478",
                    "Alice Blue", "502", "09/2023");

            ClientRequestDto alexRequest = new ClientRequestDto(
                    "Alex Green", alexCpf,
                    "alexGreen", "alex123");

            ClientRequestDto aliceRequest = new ClientRequestDto(
                    "Alice Blue", aliceCpf,
                    "aliceBlue", "alice123");

            BigDecimal bitcoinPrice = coinService.findById("bitcoin").getPriceUsd();
            BigDecimal ethereumPrice = coinService.findById("ethereum").getPriceUsd();

            CryptoWalletRequestDto alexCryptoWallet = new CryptoWalletRequestDto(BigDecimal.valueOf(50),
                    BigDecimal.valueOf(50).multiply(bitcoinPrice), "bitcoin");
            CryptoWalletRequestDto aliceCryptoWallet = new CryptoWalletRequestDto(BigDecimal.valueOf(50),
                    BigDecimal.valueOf(50).multiply(ethereumPrice), "ethereum");

            clientService.save(alexRequest);
            String alexPix = clientService.findByCpf(alexCpf).getPix();
            clientService.addCreditCard(alexCpf, alexCard);
            clientService.addBalanceOnMainWalletByPix(alexCpf, alexPix, BigDecimal.valueOf(100000));
            clientService.createCryptoWallet(alexCpf, alexCryptoWallet);

            clientService.save(aliceRequest);
            String alicePix = clientService.findByCpf(aliceCpf).getPix();
            clientService.addCreditCard(aliceCpf, aliceCard);
            clientService.addBalanceOnMainWalletByPix(aliceCpf, alicePix, BigDecimal.valueOf(100000));
            clientService.createCryptoWallet(aliceCpf, aliceCryptoWallet);
        }
    }

    private void loadExchange(){
        if (exchangeService.findAll().isEmpty()){
            BookMarketModel book = bookMarketService.save();
            exchangeService.createExchange(book);
        }
    }

}
