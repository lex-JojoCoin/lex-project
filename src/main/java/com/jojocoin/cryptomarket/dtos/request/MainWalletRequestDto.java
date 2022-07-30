package com.jojocoin.cryptomarket.dtos.request;

import com.jojocoin.cryptomarket.models.CryptoWalletModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MainWalletRequestDto {

    @NotEmpty
    private BigDecimal saldo;
    @NotEmpty
    private List<CryptoWalletModel> cryptoWallets;

}
