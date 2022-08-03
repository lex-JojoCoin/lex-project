package com.jojocoin.cryptomarket.feign;

import com.jojocoin.cryptomarket.models.DataClientModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "coincap", url = "https://api.coincap.io/v2/assets")
public interface CoinClientFeignService {

    @GetMapping
    DataClientModel getAll();
}
