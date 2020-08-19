package se128.jupiter.usercenter.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se128.jupiter.usercenter.dto.UserDto;

@FeignClient(value = "user-service")
public interface UserService {

    @GetMapping("{id}")
    UserDto getUser(@PathVariable Integer id);

    @PatchMapping("/buyCount/{id}/{goodsType}")
    UserDto addBuyCounter(@PathVariable Integer id, @PathVariable Integer goodsType);

}
