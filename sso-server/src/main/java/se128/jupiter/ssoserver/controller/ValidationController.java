package se128.jupiter.ssoserver.controller;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.ssoserver.entity.UserEntity;
import se128.jupiter.ssoserver.service.UserServiceImpl;
import se128.jupiter.ssoserver.utils.msgutils.Msg;
import se128.jupiter.ssoserver.utils.msgutils.MsgCode;
import se128.jupiter.ssoserver.utils.msgutils.MsgUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOError;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RefreshScope
public class ValidationController {
    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/redis/hasKey/{key}")
    public Boolean hasKey(@PathVariable("key") String key) {
        try {
            return template.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/redis/getKeyValue/{key}")
    public JSONObject getKeyValue(@PathVariable("key") String key){
        String jsonString = template.opsForValue().get(key);
        return JSONObject.fromObject(jsonString);
    }

     /* 校验用户名密码，一般用于内部函数 */
    @RequestMapping("/login/{username}/{password}")
    public JSONObject checkUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password) {
        UserEntity user = null;
        user = userService.checkUser(username, password);
        JSONObject ret = new JSONObject();
        String accessToken = "";
        if (user == null){  // 验证错误
            return null;
        }
        else{
            // 用户名+时间戳
            accessToken = username + System.currentTimeMillis();

            ret.put("userId", user.getUserId());
            ret.put("username", user.getUsername());
            ret.put("userType", user.getUserType());
            ret.put("accessToken", accessToken);

            // 令牌作为key 存用户信息作为value
            template.opsForValue().set(accessToken, ret.toString(), (long) (10 * 60), TimeUnit.SECONDS);
            return ret;
        }
    }

    @PostMapping("/login")
    public Msg login(HttpServletResponse response, @RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");

        JSONObject check = checkUsernameAndPassword(username, password);
        if (check == null){
            System.out.println("user is null");
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGIN_USER_ERROR_MSG);
        }
        if (check.getInt("userType") == -1){
            template.delete(check.getString("accessToken"));
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.BAN_USER_ERROR_MSG);
        }
        else {
            try{
                Cookie cookie = new Cookie("accessToken", check.getString("accessToken"));
                cookie.setMaxAge(60 * 10);
                // 设置域
                // cookie.setDomain("localhost");
                cookie.setPath("/");
                response.addCookie(cookie);
            } catch(Exception e){
                e.printStackTrace();
            }
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, check);
        }
    }

    @PostMapping("/logout")
    public Msg logout(HttpServletRequest request, HttpServletResponse response){
        // 找accessToken
        String accessToken  = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    accessToken = cookie.getValue();
                }
            }
        }

        if("".equals(accessToken) || !hasKey(accessToken)){
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.LOGOUT_ERR_MSG);
        }
        else{
            // 删除key和cookie
            template.delete(accessToken);
            Cookie newCookie = new Cookie("accessToken", null);
            newCookie.setMaxAge(0);
            newCookie.setPath("/");
            response.addCookie(newCookie);
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGOUT_SUCCESS_MSG);
        }
    }

    @RequestMapping("/checkSession")
    public Msg checkSession(HttpServletRequest request){
        // 找accessToken
        String accessToken  = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    accessToken = cookie.getValue();
                }
            }
        }

        if("".equals(accessToken)){
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        }
        else if(hasKey(accessToken)){
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, getKeyValue(accessToken));
        }
        else{
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        }

    }
}
