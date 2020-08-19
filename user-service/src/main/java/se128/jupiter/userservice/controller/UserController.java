package se128.jupiter.userservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import se128.jupiter.userservice.entity.UserEntity;
import se128.jupiter.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RefreshScope
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("{id}")
    public UserEntity getUser(@PathVariable Integer id){
        logger.info("getUserById: "+ id);
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserEntity> getAllUser()
    {
        logger.info("getAllUsers");
        List<UserEntity> users = userService.getAllUsers();
        return users;
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity userEntity) {
        logger.info("saveUser");
        return userService.saveUser(userEntity);
    }


    @PutMapping
    public UserEntity editUser(@RequestBody UserEntity userEntity) {
        logger.info("editUser");
        UserEntity user = userService.editUser(userEntity);
        return user;
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity userEntity) {
        logger.info("login");
        return userService.login(userEntity);
    }

}
