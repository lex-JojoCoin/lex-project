package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.PurchaseOrderRequestDto;
import com.jojocoin.cryptomarket.dtos.request.SaleOrderRequestDto;
import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.exceptions.*;
import com.jojocoin.cryptomarket.models.*;
import com.jojocoin.cryptomarket.repository.BookMarketRepository;
import com.jojocoin.cryptomarket.services.interfaces.BookMarketService;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import com.jojocoin.cryptomarket.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class BookMarketServiceImpl implements BookMarketService {

    private final BookMarketRepository repository;
    private OrderService orderService;
    private final CoinService coinService;
    private final ClientService clientService;

    public BookMarketModel findBook(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public BookMarketModel save(){
        BookMarketModel book = new BookMarketModel(1L, new ArrayList<>(), new ArrayList<>());
        return repository.save(book);
    }

    public BookMarketModel createSaleOrder(SaleOrderRequestDto request) {
        ClientModel client = clientService.findByCpf(request.getCpf());
        verifyClientCanCreateSaleOrder(client, request);
        CoinModel coin = coinService.findById(request.getCoin());

        OrderModel order =
                new OrderModel(null, client,
                        OrderStatus.OPEN, OrderType.SALE, request.getAmount(),
                        BigDecimal.valueOf(request.getAmount()).multiply(coin.getPriceUsd()) ,coin);

        orderService.save(order);
        BookMarketModel book = findBook(1L);
        book.getSaleOrders().add(order);
        return repository.save(book);
    }

    public BookMarketModel createPurchaseOrder(PurchaseOrderRequestDto request) {

        CoinModel coin = coinService.findById(request.getCoin());
        ClientModel client = clientService.findByCpf(request.getCpf());
        verifyClientHaveSufficientBalance(client, request, coin);

        OrderModel order =
                new OrderModel(null, client,
                        OrderStatus.OPEN, OrderType.PURCHASE, request.getAmount(),
                        BigDecimal.valueOf(request.getAmount()).multiply(request.getPrice()), coin);
        orderService.save(order);
        BookMarketModel book = findBook(1L);
        book.getPurchaseOrders().add(order);
        return repository.save(book);
    }

    public OrderModel findOrder(Long orderId){
        OrderModel order = orderService.findById(orderId);
        BookMarketModel book = findBook(1L);
        List<List<OrderModel>> orders = List.of(book.getPurchaseOrders(), book.getSaleOrders());
        return orders
                .stream()
                .flatMap(Collection::stream)
                .filter(x -> x.equals(order))
                .findAny().orElseThrow(OrderNotFoundException::new);
    }

    public void closeOrder(OrderModel order){
        BookMarketModel book = findBook(1L);
        List<List<OrderModel>> orders = List.of(book.getPurchaseOrders(), book.getSaleOrders());
        orders.stream()
                .flatMap(Collection::stream)
                .filter(x -> x.equals(order))
                .forEach(OrderModel::closeOrder);
        repository.save(book);
    }

    private void verifyClientCanCreateSaleOrder(ClientModel client, SaleOrderRequestDto request){
        CryptoWalletModel crypto = client.getMainWallet().getCryptoWalletModels().stream()
                .filter(x -> x.getCoin().getId().equals(request.getCoin()))
                .findFirst().orElseThrow(()-> new CryptoWalletNotFoundException(request.getCoin()));
        if (crypto.getAmount().compareTo(BigDecimal.valueOf(request.getAmount())) < 0)
            throw new InsufficientCoinsException();
    }

    private void verifyClientHaveSufficientBalance(ClientModel client, PurchaseOrderRequestDto request, CoinModel coin){
        BigDecimal mainWalletBalance = client.getMainWallet().getBalance();
        BigDecimal orderBalance = BigDecimal.valueOf(request.getAmount()).multiply(coin.getPriceUsd());
        if (mainWalletBalance.compareTo(orderBalance) < 0){
            throw new InsufficientBalanceException();
        }
    }
}
