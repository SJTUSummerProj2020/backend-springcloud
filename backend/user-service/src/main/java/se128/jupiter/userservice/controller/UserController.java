package se128.jupiter.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.msgutils.Msg;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    Msg getAllUser()
    {
        return null;
    }

}
