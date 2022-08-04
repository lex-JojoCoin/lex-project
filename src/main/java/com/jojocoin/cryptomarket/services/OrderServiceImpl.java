package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.OrderRequestDto;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.ClientModel;
import com.jojocoin.cryptomarket.models.CoinModel;
import com.jojocoin.cryptomarket.models.OrderModel;
import com.jojocoin.cryptomarket.repository.OrderRepository;
import com.jojocoin.cryptomarket.services.interfaces.BookMarketService;
import com.jojocoin.cryptomarket.services.interfaces.ClientService;
import com.jojocoin.cryptomarket.services.interfaces.CoinService;
import com.jojocoin.cryptomarket.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final CoinService coinService;
    private final ClientService clientService;


    public List<OrderModel> findAll() {
        return repository.findAll();
    }

    public OrderModel findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public OrderModel save(OrderRequestDto request) {

        CoinModel coin = coinService.findById(request.getCoin().getId());
        ClientModel client = clientService.findById(request.getClient().getClientId());

        OrderModel model =
                new OrderModel(
                        null,
                        client,
                        request.getStatus(),
                        request.getType(),
                        request.getAmount(),
                        request.getValue(),
                        coin
                );

        return repository.save(model);
    }

    public OrderModel update(UUID id, OrderRequestDto request) {

        OrderModel order = findById(id);
        BeanUtils.copyProperties(request, order);
        return repository.save(order);

    }

    public void deleteById(UUID id) {

        OrderModel order = findById(id);
        try {
            repository.deleteById(order.getId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }

    }
}
