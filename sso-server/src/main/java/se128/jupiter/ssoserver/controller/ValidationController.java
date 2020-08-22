package se128.jupiter.ssoserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            //用户名+时间戳（这里只是demo，正常项目的令牌应该要更为复杂）
            flag = username + System.currentTimeMillis();
            //令牌作为key，存用户id作为value（或者直接存储可暴露的部分用户信息也行）设置过期时间（我这里设置3分钟）
            template.opsForValue().set(flag, "1", (long) (3 * 60), TimeUnit.SECONDS);
        }
        return flag;
    }

    @RequestMapping("/checkSession")
    public boolean checkSession(){
        return true;
    }

}
