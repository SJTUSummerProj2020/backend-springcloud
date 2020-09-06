package se128.jupiter.goodsservice.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se128.jupiter.goodsservice.dto.UserDto;

@FeignClient(name="user-service")
public interface UserFeign {
    @GetMapping("/getUserById/{id}")
    UserDto getUser(@PathVariable Integer id);
}
