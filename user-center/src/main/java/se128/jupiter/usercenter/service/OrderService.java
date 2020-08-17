package se128.jupiter.usercenter.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se128.jupiter.usercenter.dto.OrderDto;

import java.util.List;

@FeignClient(name="order-service")
public interface OrderService {
    @GetMapping("userOrderList/{userId}")
    List<OrderDto> getUserOrderList(@PathVariable String userId);
}
