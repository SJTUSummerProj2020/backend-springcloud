package se128.jupiter.orderservice.service;

import se128.jupiter.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(Integer id);
}
