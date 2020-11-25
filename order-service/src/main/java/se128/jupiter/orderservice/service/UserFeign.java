package se128.jupiter.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.orderservice.dto.GoodsDetailDto;
import se128.jupiter.orderservice.dto.UserDto;
import se128.jupiter.orderservice.utils.msgutils.Msg;

@FeignClient(name="user-service")
public interface UserFeign {

    @RequestMapping("/getUserById1/{userId}")
    UserDto getUserById1(@PathVariable Integer userId);

    @RequestMapping("/editUser")
    Msg editUser(@RequestBody UserDto userEntity);

    @PatchMapping("/buyCount/{id}/{goodsType}")
    UserDto addBuy(@PathVariable Integer id, @PathVariable Integer goodsType);
}
