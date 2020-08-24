package se128.jupiter.zuulserver.service;


import net.sf.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@FeignClient(name="sso-server")
public interface SsoFeign {
    @RequestMapping("/redis/hasKey/{key}")
    Boolean hasKey(@PathVariable("key") String key);

    @RequestMapping("/redis/getKeyValue/{key}")
    JSONObject getKeyValue(@PathVariable("key") String key);

    @RequestMapping("/login/{username}/{password}")
    JSONObject checkUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password);

    @PostMapping("/login")
    JSONObject login(@RequestBody Map<String, String> params);
}
