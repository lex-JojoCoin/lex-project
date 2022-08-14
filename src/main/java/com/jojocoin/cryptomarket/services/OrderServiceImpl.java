package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.SaleOrderRequestDto;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.OrderModel;
import com.jojocoin.cryptomarket.repository.OrderRepository;
import com.jojocoin.cryptomarket.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    public List<OrderModel> findAll() {
        return repository.findAll();
    }

    public OrderModel findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public OrderModel save(OrderModel orderModel){
        return repository.save(orderModel);
    }

    public OrderModel update(Long id, SaleOrderRequestDto request) {
        OrderModel order = findById(id);
        BeanUtils.copyProperties(request, order);
        return repository.save(order);
    }

    public void deleteById(Long id) {
        OrderModel order = findById(id);
        try {
            repository.deleteById(order.getId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }
    }
}
