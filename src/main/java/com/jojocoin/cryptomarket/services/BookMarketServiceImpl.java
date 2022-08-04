package com.jojocoin.cryptomarket.services;

import com.jojocoin.cryptomarket.dtos.request.BookMarketRequestDto;
import com.jojocoin.cryptomarket.enums.OrderType;
import com.jojocoin.cryptomarket.exceptions.DataIntegrityException;
import com.jojocoin.cryptomarket.exceptions.ResourceNotFoundException;
import com.jojocoin.cryptomarket.models.BookMarketModel;
import com.jojocoin.cryptomarket.repository.BookMarketRepository;
import com.jojocoin.cryptomarket.services.interfaces.BookMarketService;
import com.jojocoin.cryptomarket.services.interfaces.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookMarketServiceImpl implements BookMarketService {

    private final BookMarketRepository repository;

    private OrderService orderService;


    public List<BookMarketModel> findAll() {
        return repository.findAll();
    }

    public BookMarketModel findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public BookMarketModel save(BookMarketRequestDto request) {

        BookMarketModel model =
                new BookMarketModel(
                        null,
                        request.getSaleOrders(),
                        request.getPurchaseOrders()
                );
        return repository.save(model);
    }


    public BookMarketModel update(UUID id, BookMarketRequestDto request) {

        BookMarketModel book = findById(id);
        BeanUtils.copyProperties(request, book);
        return repository.save(book);
    }


    public void deleteById(UUID id) {

        BookMarketModel book = findById(id);
        try {
            repository.deleteById(book.getId());
        } catch (DataIntegrityViolationException exception) {
            throw new DataIntegrityException();
        }

    }
}
