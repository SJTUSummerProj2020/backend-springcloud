package se128.jupiter.orderservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.orderservice.entity.COrderEntity;
import se128.jupiter.orderservice.service.OrderServiceImpl;

import java.util.List;

@RestController
@RefreshScope
public class OrderController {
    //    private final OrderService orderService;
//    private  static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
//
//    @Autowired
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public Msg getAllOrder()
//    {
//        logger.info("getAllOrders");
//        List<COrderEntity> COrderEntities = orderService.getAllOrders();
//        JSONObject data = new JSONObject();
//        JSONArray orderList = JSONArray.fromObject(COrderEntities);
//        data.put("COrderEntities", orderList.toString());
//        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
//    }
//
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
//
//    @GetMapping("/{id}")
//    public Msg getOrderById(@PathVariable Integer id)
//    {
//        logger.info("getUserById = " + id);
//        COrderEntity COrderEntity =orderService.getOrderById(id);
//        JSONObject data = JSONObject.fromObject(COrderEntity);
//        return MsgUtil.makeMsg(MsgCode.SUCCESS, data);
//    }
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("{id}")
    public COrderEntity getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public COrderEntity saveOrder(@RequestBody COrderEntity COrderEntity) {
        return orderService.saveOrder(COrderEntity);
    }

    @GetMapping("userOrderList/{userId}")
    public List<COrderEntity> getOrderListByUser(@PathVariable String userId){
        return orderService.getOrderListByUser(userId);
    }

    @Value("${envName")
    private String envName;
    @GetMapping("getEnvName")
    public String getEnvName(){
        return envName;
    }

}
