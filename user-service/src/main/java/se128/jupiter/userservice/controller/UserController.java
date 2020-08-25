package se128.jupiter.userservice.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import se128.jupiter.userservice.entity.UserEntity;
import se128.jupiter.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.userservice.util.msgutils.Msg;
import se128.jupiter.userservice.util.msgutils.MsgCode;
import se128.jupiter.userservice.util.msgutils.MsgUtil;

import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("/getUserById/{id}")
    public Msg getUser(@PathVariable Integer id){
        logger.info("getUserById: "+ id);
        UserEntity userEntity = userService.getUser(id);
        JSONObject data = JSONObject.fromObject(userEntity);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, data);
    }

    @GetMapping("/getAllUsers")
    public Msg getAllUser() {
        logger.info("getAllUsers");
        List<UserEntity> users = userService.getAllUsers();
        JSONObject data = new JSONObject();
        JSONArray userList = JSONArray.fromObject(users);
        data.put("user", userList.toString());
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

    @PostMapping("/register")
    public Msg register(@RequestBody UserEntity userEntity) {
        logger.info("register");
        userEntity.setUserType(1);    // customer
        userEntity.setBuy0(0);
        userEntity.setBuy1(0);
        userEntity.setBuy2(0);
        userEntity.setBuy3(0);
        userEntity.setBuy4(0);
        userEntity.setBuy5(0);
        UserEntity user1 = userService.saveUser(userEntity);

        if (user1 != null) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG);
        } else {
            return MsgUtil.makeMsg(MsgCode.REGISTER_USER_ERROR);
        }
    }

    @PostMapping("/login")
    public Msg login(@RequestBody Map<String, String> params){

        return null;
    }

    @RequestMapping("/editUser")
    public Msg editUser(@RequestBody UserEntity userEntity) {
        logger.info("editUser");
        UserEntity user1 = userService.editUser(userEntity);
        JSONObject data = JSONObject.fromObject(user1);
        return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS, data);
    }

    @PatchMapping("/ban/{id}")
    public UserEntity banUser(@PathVariable Integer id) {
        logger.info("banUser");
        return userService.banUser(id);
    }

    @PatchMapping("/unban/{id}")
    public UserEntity unbanUser(@PathVariable Integer id) {
        logger.info("unbanUser");
        return userService.unbanUser(id);
    }

    @PostMapping("/changeUserStatusByUserId")
    public Msg changeUserStatusByUserId(@RequestBody Map<String, String> params) {
        Integer userId = Integer.valueOf(params.get("userId"));
        logger.info("changeUserStatusByUserId = " + userId);
        UserEntity user = userService.changeUserStatusByUserId(userId);
        if (user != null) {
            return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS);
        } else {
            return MsgUtil.makeMsg(MsgCode.EDIT_ERROR);
        }
    }

    @PatchMapping("/buyCount/{id}/{goodsType}")
    public UserEntity addBuy(@PathVariable Integer id, @PathVariable Integer goodsType) {
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
