package se128.jupiter.feignserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se128.jupiter.feignserver.service.UserService;
import util.msgutils.Msg;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Msg getAllUser(){
        return userService.getAllUser();
    }
}
