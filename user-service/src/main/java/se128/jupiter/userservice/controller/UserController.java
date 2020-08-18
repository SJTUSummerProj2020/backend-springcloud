package se128.jupiter.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import se128.jupiter.userservice.entity.UserEntity;
import se128.jupiter.userservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RefreshScope
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //  @GetMapping
//    public Msg getAllUser()
//    {
//        logger.info("getAllUsers");
//        List<UserEntity> users = userService.getAllUsers();
//        JSONObject data = new JSONObject();
//        JSONArray orderList = JSONArray.fromObject(users);
//        data.put("users", orderList.toString());
//        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
//    }
//
//    @PostMapping
//    public Msg register(@RequestBody UserEntity userEntity)
//    {
//        logger.info("register");
//        userEntity.setUserType(Constant.Customer);
//        userEntity.setBuy0(0);
//        userEntity.setBuy1(0);
//        userEntity.setBuy2(0);
//        userEntity.setBuy3(0);
//        UserEntity user1 = userService.addUser(userEntity);
//
//        if (user1 != null) {
//            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.REGISTER_SUCCESS_MSG);
//        } else {
//            return MsgUtil.makeMsg(MsgCode.REGISTER_USER_ERROR);
//        }
//    }
//
//    @GetMapping("/{id}")
//    public Msg getUserById(@PathVariable Integer id)
//    {
//        logger.info("getUserById = " + id);
//        UserEntity userEntity = userService.getUserByUserId(id);
//        JSONObject data = JSONObject.fromObject(userEntity);
//        return MsgUtil.makeMsg(MsgCode.SUCCESS, data);
//    }
//
//    @PutMapping("/{id}")
//    public Msg editUser(@RequestBody UserEntity userEntity, @PathVariable String id) {
//        logger.info("editUser");
//        UserEntity user1 = userService.editUser(userEntity);
//        JSONObject data = JSONObject.fromObject(user1);
//        return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS, data);
//    }
    @GetMapping("{id}")
    public UserEntity getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

    @Value("${envName}")
    private String envName;
    @GetMapping("getEnvName")
    public String getEnvName(){
        return envName;
    }

}
