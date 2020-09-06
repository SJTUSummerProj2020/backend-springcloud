package se128.jupiter.orderservice.controller;

import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.orderservice.entity.COrderEntity;
import se128.jupiter.orderservice.service.OrderServiceImpl;
import se128.jupiter.orderservice.service.SsoFeign;
import se128.jupiter.orderservice.utils.msgutils.Msg;
import se128.jupiter.orderservice.utils.msgutils.MsgCode;
import se128.jupiter.orderservice.utils.msgutils.MsgUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private SsoFeign ssoFeign;

    private  static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("{id}")
    public COrderEntity getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

    @GetMapping()
    public List<COrderEntity> getAllOrder() {
        return orderService.getAllOrder();
    }

    @PutMapping("/addOrder")
    public Msg addOrder(HttpServletRequest request, @RequestBody Map<String, String> params) {
        COrderEntity order = new COrderEntity();

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
        JSONObject user = ssoFeign.getKeyValue(accessToken);
        if(user == null){
            return MsgUtil.makeMsg(MsgCode.ADD_ERROR, MsgUtil.BUY_ERROR_MSG);
        }
        Integer userId = user.getInt("userId");
        Integer number = Integer.valueOf(params.get("number"));
        Integer detailId = Integer.valueOf(params.get("detailId"));

        System.out.println("userId:" + userId.toString());
        System.out.println("number:" + number.toString());
        System.out.println("detailId:" + detailId.toString());

        order.setUserId(userId);
        order.setNumber(number);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        order.setTime(timestamp);

        order.setSourceId("54749110");

        COrderEntity order1 = orderService.addOrder(order, detailId);

        if (order1 == null) {
            return MsgUtil.makeMsg(MsgCode.ADD_ERROR, MsgUtil.BUY_ERROR_MSG);
        }

        JSONObject data = JSONObject.fromObject(order1);
        return MsgUtil.makeMsg(MsgCode.ADD_SUCCESS, MsgUtil.BUY_SUCCESS_MSG, data);

    }

    @GetMapping("userOrderList/{userId}")
    public List<COrderEntity> getOrderListByUser(@PathVariable Integer userId){
        return orderService.getOrderListByUser(userId);
    }

    @Value("${envName}")
    private String envName;
    @GetMapping("getEnvName")
    public String getEnvName(){
        return envName;
    }
}
