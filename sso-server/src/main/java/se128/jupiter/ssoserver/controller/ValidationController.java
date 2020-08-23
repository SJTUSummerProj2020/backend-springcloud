package se128.jupiter.ssoserver.controller;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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

    @RequestMapping("/redis/hasKey/{key}")
    public Boolean hasKey(@PathVariable("key") String key) {
        try {
            return template.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 校验用户名密码，成功则返回通行令牌（这里写死huanzi/123456）
     */
    @RequestMapping("/login/{username}/{password}")
    public String checkUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password) {
        String flag = null;
        if ("huanzi".equals(username) && "123456".equals(password)) {
            // 用户名+时间戳（这里只是demo，正常项目的令牌应该要更为复杂）
            flag = username + System.currentTimeMillis();
            // 令牌作为key，存用户id作为value（或者直接存储可暴露的部分用户信息也行）设置过期时间（我这里设置3分钟）
            template.opsForValue().set(flag, "1", (long) (3 * 60), TimeUnit.SECONDS);
        }
        return flag;
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response, @RequestBody Map<String, String> params){
        String username = params.get("username");
        String password = params.get("password");

        String check = checkUsernameAndPassword(username, password);
        if(!StringUtils.isEmpty(check)){
            try{
                Cookie cookie = new Cookie("accessToken", check);
                cookie.setMaxAge(60 * 3);
                // 设置域
                // cookie.setDomain("localhost:8801");
                cookie.setPath("/");
                response.addCookie(cookie);
            } catch(Exception e){
                e.printStackTrace();
            }
            return "login success";
        }
        return "login failed";
    }

    @RequestMapping("/checkSession")
    public boolean checkSession(){

        return true;
    }

}
