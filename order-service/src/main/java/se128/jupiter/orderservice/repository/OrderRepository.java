package se128.jupiter.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se128.jupiter.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
