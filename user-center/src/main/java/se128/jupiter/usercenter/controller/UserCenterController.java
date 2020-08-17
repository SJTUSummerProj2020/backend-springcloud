package se128.jupiter.usercenter.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se128.jupiter.usercenter.dto.OrderDto;
import se128.jupiter.usercenter.dto.UserDto;
import se128.jupiter.usercenter.service.OrderService;
import se128.jupiter.usercenter.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@DefaultProperties(defaultFallback = "fallback")
public class UserCenterController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="500")
    },threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "101"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
    })
    @GetMapping("userOrderList/{userId}")
    public Object getUserOrderList(@PathVariable String userId){
        Map<String,Object> resultMap=new HashMap<>();
        List<OrderDto> orderDtoList=orderService.getUserOrderList(userId);
        UserDto userDto=userService.getUser(userId);
        resultMap.put("user",userDto);
        resultMap.put("orderList",orderDtoList);
        return resultMap;
    }

    private Object error(String userId) {
        return "请求出错";
    }

    private Object fallback(){
        return "错误";
    }
}
