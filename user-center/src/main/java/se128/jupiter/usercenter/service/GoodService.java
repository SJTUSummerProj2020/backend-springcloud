package se128.jupiter.usercenter.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se128.jupiter.usercenter.dto.GoodsDto;

@FeignClient(name="goods-service")
public interface GoodService {

    @GetMapping("{id}")
    GoodsDto getGoods(@PathVariable Integer id);
}
