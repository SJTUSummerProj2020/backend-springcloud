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

    @Autowired
    private OrderServiceImpl orderService;

    private  static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @GetMapping("{id}")
    public COrderEntity getOrder(@PathVariable Integer id) {
        return orderService.getOrder(id);
    }

    @GetMapping()
    public List<COrderEntity> getAllOrder() {
        return orderService.getAllOrder();
    }

    @PostMapping
    public COrderEntity saveOrder(@RequestBody COrderEntity COrderEntity) {
        return orderService.saveOrder(COrderEntity);
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
