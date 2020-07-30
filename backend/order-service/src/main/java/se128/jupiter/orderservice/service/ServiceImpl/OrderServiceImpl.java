package se128.jupiter.orderservice.service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se128.jupiter.orderservice.entity.Order;
import se128.jupiter.orderservice.repository.OrderRepository;
import se128.jupiter.orderservice.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }
}
