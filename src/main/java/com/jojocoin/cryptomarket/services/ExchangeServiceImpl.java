package com.jojocoin.cryptomarket.services;


import com.jojocoin.cryptomarket.dtos.request.*;
import com.jojocoin.cryptomarket.dtos.response.ClientResponseDto;
import com.jojocoin.cryptomarket.dtos.response.OperationResponse;
import com.jojocoin.cryptomarket.enums.OrderStatus;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.exceptions.*;
import com.jojocoin.cryptomarket.models.*;
import com.jojocoin.cryptomarket.repository.ExchangeRepository;
import com.jojocoin.cryptomarket.services.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRepository repository;
    private final ClientService clientService;
    private final BookMarketService bookService;

    public List<ExchangeModel> findAll(){
        return repository.findAll();
    }

    public ExchangeModel createExchange(BookMarketModel book){
        ExchangeModel exchange = new ExchangeModel(1L, BigDecimal.valueOf(100), book);
        return repository.save(exchange);
    }

    public BookMarketModel createSaleOrder(SaleOrderRequestDto request) {
        return bookService.createSaleOrder(request);
    }

    public BookMarketModel createPurchaseOrder(PurchaseOrderRequestDto request) {
        return bookService.createPurchaseOrder(request);
    }

    public OperationResponse purchaseCoins(OperationRequest request) {
        //Busco a ordem no banco de dados
        OrderModel order = bookService.findOrder(request.getOrderId());

        //Verifico se a ordem está aberta
        verifyTheOrderStatus(order);

        //Verifico se ela é de venda
        isSaleOrder(order);

        //Comprador
        ClientModel buyer = clientService.findByCpf(request.getClientCpf());

        //Calcula o quanto deve ser pago
        BigDecimal balance = order.getValue().multiply(BigDecimal.valueOf(order.getAmount()));
        double fee = 0.005;
        BigDecimal totalBuyer = balance.add(balance.multiply(BigDecimal.valueOf(fee)));
        BigDecimal feeBuyer = totalBuyer.subtract(balance);
        //verifica se o comprador pode realizar a compra
        verifyBuyerBalance(buyer, totalBuyer);

        //Atualizar ou criar a crypto wallet do comprador
        if(verifyCryptoWalletDoesNotExists(buyer.getMainWallet().getCryptoWalletModels(), order)){
            clientService.createCryptoWallet(
                    buyer.getCpf(),
                    new CryptoWalletRequestDto(
                            BigDecimal.valueOf(order.getAmount()), balance
                            , order.getCoin().getId()));
        } else {
            clientService.updateAddCryptoWallet(
                    buyer.getCpf(),
                    order.getCoin().getId(),
                    new CryptoWalletRequestDto(
                            BigDecimal.valueOf(order.getAmount()), balance
                            , order.getCoin().getId()));
        }

        //subtrair o saldo do comprador
        buyer.getMainWallet().subtractBalance(totalBuyer);
        clientService.update(buyer);

        //Vendedor
        ClientModel seller = order.getClient();

        //Calcula o quanto o vendedor deve receber
        BigDecimal totalSeller = balance.subtract(balance.multiply(BigDecimal.valueOf(fee)));
        BigDecimal feeSeller = balance.subtract(totalSeller);

        //Atualizando a carteira do vendendor
        clientService.updateSubCryptoWallet(
                seller.getCpf(),
                order.getCoin().getId(),
                new CryptoWalletRequestDto(
                        BigDecimal.valueOf(order.getAmount()),
                        BigDecimal.valueOf(order.getAmount()).multiply(order.getCoin().getPriceUsd())
                        ,order.getCoin().getId()));

        //Adicionar saldo na conta
        seller.getMainWallet().addBalance(totalSeller);
        clientService.update(seller);

        //Fecha a operação
        bookService.closeOrder(order);

        //Adiciona taxas
        ExchangeModel exchange = findExchange().addBalance(feeBuyer.add(feeSeller));
        repository.save(exchange);
        return new OperationResponse(
                "Transaction completed successfully!",
                new ClientResponseDto(buyer));
    }

    public OperationResponse sellCoins(OperationRequest request) {
        //Busco a ordem no banco de dados
        OrderModel order = bookService.findOrder(request.getOrderId());

        //Verifico se a ordem está aberta
        verifyTheOrderStatus(order);

        //Verifico se ela é de compra
        isPurchaseOrder(order);

        //Comprador
        ClientModel seller = clientService.findByCpf(request.getClientCpf());

        //Verifico se o vendedor tem uma carteira com a crypto moeda
        verifyCryptoWalletDoesExists(seller.getMainWallet().getCryptoWalletModels(), order);

        //Verifico se a carteira tem saldo para realizar a venda
        verifyBalanceOfCryptoWalletIsSufficientToMakeTheSale(seller.getMainWallet().getCryptoWalletModels(), order);

        //Calcular o quanto o vendedor deve receber
        BigDecimal balance = order.getValue();
        double fee = 0.005;
        BigDecimal totalSeller = balance.subtract(balance.multiply(BigDecimal.valueOf(fee)));

        //Calcula o valor da taxa
        BigDecimal feeSeller = balance.subtract(totalSeller);

        //Atualizando a carteira do vendendor
        clientService.updateSubCryptoWallet(
                seller.getCpf(),
                order.getCoin().getId(),
                new CryptoWalletRequestDto(
                        BigDecimal.valueOf(order.getAmount()),
                        BigDecimal.valueOf(order.getAmount()).multiply(order.getCoin().getPriceUsd())
                        , order.getCoin().getId()));

        //Adicionar saldo na conta
        seller.getMainWallet().addBalance(totalSeller);
        clientService.update(seller);

        //Comprador
        ClientModel buyer = order.getClient();

        //Calcula o quanto o vendedor deve pagar
        BigDecimal totalBuyer = balance.add(balance.multiply(BigDecimal.valueOf(fee)));

        //Calcula o valor da taxa
        BigDecimal feeBuyer = totalBuyer.subtract(balance);

        //verifica se o comprador pode realizar a compra
        verifyBuyerBalance(buyer, totalBuyer);

        //Atualizar ou criar a crypto wallet do comprador
        if(verifyCryptoWalletDoesNotExists(buyer.getMainWallet().getCryptoWalletModels(), order)){
            clientService.createCryptoWallet(
                    buyer.getCpf(),
                    new CryptoWalletRequestDto(
                            BigDecimal.valueOf(order.getAmount()), balance
                            , order.getCoin().getId()));
        } else {
            clientService.updateAddCryptoWallet(
                    buyer.getCpf(),
                    order.getCoin().getId(),
                    new CryptoWalletRequestDto(
                            BigDecimal.valueOf(order.getAmount()), balance
                            , order.getCoin().getId()));
        }

        //subtrair o saldo do comprador
        buyer.getMainWallet().subtractBalance(totalBuyer);
        clientService.update(buyer);

        //Fecha a operação
        bookService.closeOrder(order);

        //Adiciona taxas
        ExchangeModel exchange = findExchange().addBalance(feeBuyer.add(feeSeller));
        repository.save(exchange);
        return new OperationResponse(
                "Transaction completed successfully!",
                new ClientResponseDto(seller));
    }

    public ExchangeModel findExchange(){
        return repository.findById(1L).orElseThrow(()-> new ResourceNotFoundException(1L));
    }
    
    private void verifyTheOrderStatus(OrderModel order){
        if (order.getStatus().equals(OrderStatus.CLOSED))
            throw new IllegalOrderOperation();
    }

    private void verifyBuyerBalance(ClientModel buyer, BigDecimal total){
        if (buyer.getMainWallet().getBalance().compareTo(total) < 0)
            throw new IllegalOrderOperation(total);
    }

    private void verifyCryptoWalletDoesExists(List<CryptoWalletModel> clientCrypWallet, OrderModel order){
        if (clientCrypWallet.stream().noneMatch(x -> x.getCoin().equals(order.getCoin())))
            throw new IllegalOrderOperation(order.getCoin().getName());
    }

    private void verifyBalanceOfCryptoWalletIsSufficientToMakeTheSale(List<CryptoWalletModel> wallet, OrderModel order){
        wallet.forEach(
                x-> {
                    if (x.getCoin().equals(order.getCoin())){
                        BigDecimal balance = order.getValue().multiply(BigDecimal.valueOf(order.getAmount()));
                        if (x.getBalance().compareTo(balance) < 0)
                            throw new InsufficientBalanceException();
                    }
                }
        );
    }

    private boolean verifyCryptoWalletDoesNotExists(List<CryptoWalletModel> clientCrypWallet, OrderModel order){
        return clientCrypWallet.stream().noneMatch(x -> x.getCoin().equals(order.getCoin()));
    }

    private void isSaleOrder(OrderModel order){
        if (order.getType().equals(OrderType.PURCHASE))
            throw new IllegalPurchaseOperation();
    }

    private void isPurchaseOrder(OrderModel order){
        if (order.getType().equals(OrderType.SALE))
            throw new IllegalSaleOperation();
    }

}
