package se128.jupiter.zuulserver.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name="sso-server")
public interface SsoFeign {
    @RequestMapping("/redis/hasKey/{key}")
    Boolean hasKey(@PathVariable("key") String key);

    @RequestMapping("/login/{username}/{password}")
    String checkUsernameAndPassword(@PathVariable("username") String username, @PathVariable("password") String password);

    @RequestMapping("/checkSession")
    boolean checkSession();
}
