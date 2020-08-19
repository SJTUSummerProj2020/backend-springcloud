package se128.jupiter.userservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import se128.jupiter.userservice.entity.UserEntity;
import se128.jupiter.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
        return userService.getAllUsers();
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity userEntity) {
        logger.info("saveUser");
        return userService.saveUser(userEntity);
    }

    @PutMapping
    public UserEntity editUser(@RequestBody UserEntity userEntity) {
        logger.info("editUser");
        return userService.editUser(userEntity);
    }

    @PatchMapping("/ban/{id}")
    public UserEntity banUser(@PathVariable Integer id)
    {
        logger.info("banUser");
        return userService.banUser(id);
    }

    @PatchMapping("/unban/{id}")
    public UserEntity unbanUser(@PathVariable Integer id)
    {
        logger.info("unbanUser");
        return userService.unbanUser(id);
    }

    @PatchMapping("/buyCount/{id}/{goodsType}")
    public UserEntity addBuy(@PathVariable Integer id, @PathVariable Integer goodsType)
    {
        logger.info("addView");
        return userService.addBuy(id,goodsType);
    }

    @PostMapping("/checkUser")
    public UserEntity checkUser(@RequestBody Map<String, String> params) {
        logger.info("checkUser");
        String username = params.get("username");
        String password = params.get("password");
        return userService.checkUser(username,password);
    }

}
