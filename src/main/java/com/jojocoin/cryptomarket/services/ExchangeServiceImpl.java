package com.jojocoin.cryptomarket.services;


import com.jojocoin.cryptomarket.dtos.request.PurchaseRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleRequestDto;
import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.exceptions.InsuficientFundsException;
import com.jojocoin.cryptomarket.models.*;
import com.jojocoin.cryptomarket.repository.BookMarketRepository;
import com.jojocoin.cryptomarket.repository.ClientRepository;
import com.jojocoin.cryptomarket.repository.ExchangeRepository;
import com.jojocoin.cryptomarket.repository.OrderRepository;
import com.jojocoin.cryptomarket.services.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository repository;
    private final OrderService orderService;
    private final BookMarketService bookService;
    private final CardService cardService;
    private final ClientRepository clientRepository;
    private final CoinService coinService;
    private final OrderRepository orderRepository;

    public ExchangeModel purchaseCoins(PurchaseRequestDto request) {
        return null;
    }

    public SaleModel sellCoins(SaleRequestDto request) {
        Optional<ClientModel> seller = clientRepository.findByCpf(request.getSellerCpf());
        ClientModel client = new ClientModel();
        BeanUtils.copyProperties(seller, client);
        CoinModel coin = coinService.findById(request.getCoin().getId());
        Integer amount = request.getQuantity();

        walletOperator(client, coin, amount);
        registerAsOrder(request, client, coin);

        return new SaleModel(
                request.getSellerCpf(),
                coin,
                amount,
                request.getSellingPrice()
        );
    }

    private void registerAsOrder(SaleRequestDto request, ClientModel client, CoinModel coin) {
        OrderModel order = new OrderModel(
                null,
                client,
                OrderStatus.OPEN,
                OrderType.SALE,
                request.getQuantity(),
                request.getSellingPrice(),
                coin
        );
        orderRepository.save(order);
    }

    private Boolean walletOperator(ClientModel client, CoinModel coin, Integer amount) {
        List<CryptoWalletModel> cryptoWallets = client.getMainWallet().getCryptoWalletModels();
        for (CryptoWalletModel wallet : cryptoWallets) {
            if (wallet.getCoin().getName().equals(coin.getName())) {
                BigDecimal newAmount = wallet.getAmount().subtract(BigDecimal.valueOf(amount));
                if (newAmount.compareTo(BigDecimal.ZERO) >= 0) return true;
            }
        }
        throw new InsuficientFundsException("Selling order interrupted.");
    }
}
