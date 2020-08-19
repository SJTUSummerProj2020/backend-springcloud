package se128.jupiter.usercenter.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.usercenter.dto.GoodsDto;
import se128.jupiter.usercenter.dto.OrderDto;
import se128.jupiter.usercenter.dto.UserDto;
import se128.jupiter.usercenter.service.GoodService;
import se128.jupiter.usercenter.service.OrderService;
import se128.jupiter.usercenter.service.UserService;
import se128.jupiter.usercenter.util.msgutils.Msg;
import se128.jupiter.usercenter.util.msgutils.MsgCode;
import se128.jupiter.usercenter.util.msgutils.MsgUtil;

import java.util.List;

@RestController
@DefaultProperties(defaultFallback = "fallback")
public class UserCenterController {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodService goodService;

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
    public Msg getUserOrderList(@PathVariable Integer userId){
        List<OrderDto> orderDtoList=orderService.getUserOrderList(userId);
        JSONArray orderList = JSONArray.fromObject(orderDtoList);
        JSONObject data = new JSONObject();
        data.put("order", orderList);
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

    @PostMapping()
    public Msg addOrder(@RequestBody OrderDto orderDto)
    {
        logger.info("addOrder");
        OrderDto order = orderService.saveOrder(orderDto);
        Integer userId = orderDto.getUserId();
        Integer goodsId = order.getGoodsId();
        GoodsDto goods= goodService.getGoods(goodsId);
        userService.addBuyCounter(userId,goods.getGoodsType());
        JSONObject data = JSONObject.fromObject(order);
        return MsgUtil.makeMsg(MsgCode.ADD_SUCCESS, MsgUtil.BUY_SUCCESS_MSG, data);
    }

    private Msg error(String userId) {
        return MsgUtil.makeMsg(MsgCode.DATA_ERROR);
    }

    private Msg fallback(){
        return MsgUtil.makeMsg(MsgCode.DATA_ERROR);
    }
}
