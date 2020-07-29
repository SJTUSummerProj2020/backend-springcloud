package se128.jupiter.feignserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import util.msgutils.Msg;

@FeignClient(value = "user-service")
public interface UserService {

    @GetMapping
    Msg getAllUser();
}
