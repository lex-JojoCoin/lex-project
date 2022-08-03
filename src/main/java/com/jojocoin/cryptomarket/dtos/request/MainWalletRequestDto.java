package com.jojocoin.cryptomarket.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MainWalletRequestDto {

<<<<<<< HEAD
    @NotEmpty
    private List<CryptoWalletModel> cryptoWallets;
    @NotEmpty
    private BigDecimal saldo;

=======
    @NotNull
    private BigDecimal balance;
>>>>>>> main
}
