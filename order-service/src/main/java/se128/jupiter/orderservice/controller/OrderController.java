package se128.jupiter.orderservice.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.orderservice.entity.Order;
import se128.jupiter.orderservice.service.OrderService;
import util.constant.Constant;
import util.msgutils.Msg;
import util.msgutils.MsgCode;
import util.msgutils.MsgUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    private final OrderService orderService;
    private  static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Msg getAllOrder()
    {
        logger.info("getAllOrders");
        List<Order> orders = orderService.getAllOrders();
        JSONObject data = new JSONObject();
        JSONArray orderList = JSONArray.fromObject(orders);
        data.put("orders", orderList.toString());
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

//    @PostMapping
//    @RequestMapping("/addOrder")
//    public Msg addOrder(@RequestBody Map<String, String> params) {
//
//        logger.info("addOrder");
//
//        Order order = new Order();
//
//        JSONObject user = SessionUtil.getAuth();
//        Integer userId = user.getInt(Constant.USER_ID);
//        Integer number = Integer.valueOf(params.get("number"));
//        Integer detailId = Integer.valueOf(params.get("detailId"));
//
//        System.out.println("userId:" + userId.toString());
//        System.out.println("number:" + number.toString());
//        System.out.println("detailId:" + detailId.toString());
//
//        order.setUserId(userId);
//        order.setNumber(number);
//
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        order.setTime(timestamp.toString());
//
//        order.setSourceId(54749110);
//
//        Order order1 = orderService.addOrder(order);
//
//        if (order1 == null) {
//            return MsgUtil.makeMsg(MsgCode.ADD_ERROR, MsgUtil.BUY_ERROR_MSG);
//        }
//
//        JSONObject data = JSONObject.fromObject(order1);
//        return MsgUtil.makeMsg(MsgCode.ADD_SUCCESS, MsgUtil.BUY_SUCCESS_MSG, data);
//    }

    @GetMapping("/{id}")
    public Msg getOrderById(@PathVariable Integer id)
    {
        logger.info("getUserById = " + id);
        Order order =orderService.getOrderById(id);
        JSONObject data = JSONObject.fromObject(order);
        return MsgUtil.makeMsg(MsgCode.SUCCESS, data);
    }


}
