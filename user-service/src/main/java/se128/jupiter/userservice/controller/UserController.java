package se128.jupiter.userservice.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.userservice.entity.User;
import se128.jupiter.userservice.service.UserService;
import util.constant.Constant;
import util.msgutils.Msg;
import util.msgutils.MsgCode;
import util.msgutils.MsgUtil;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Msg getAllUser()
    {
        logger.info("getAllUsers");
        List<User> users = userService.getAllUsers();
        JSONObject data = new JSONObject();
        JSONArray orderList = JSONArray.fromObject(users);
        data.put("users", orderList.toString());
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

    @PostMapping
    public Msg register(@RequestBody User user)
    {
        logger.info("register");
        user.setUserType(Constant.Customer);
        user.setBuy0(0);
        user.setBuy1(0);
        user.setBuy2(0);
        user.setBuy3(0);
        User user1 = userService.addUser(user);

        if (user1 != null) {
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG);
        } else {
            return MsgUtil.makeMsg(MsgCode.REGISTER_USER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Msg getUserById(@PathVariable Integer id)
    {
        logger.info("getUserById = " + id);
        User user = userService.getUserByUserId(id);
        JSONObject data = JSONObject.fromObject(user);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, data);
    }

    @PutMapping("/{id}")
    public Msg editUser(@RequestBody User user, @PathVariable String id) {
        logger.info("editUser");
        User user1 = userService.editUser(user);
        JSONObject data = JSONObject.fromObject(user1);
        return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS, data);
    }

}
