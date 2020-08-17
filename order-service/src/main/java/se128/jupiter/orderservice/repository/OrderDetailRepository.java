package se128.jupiter.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se128.jupiter.orderservice.entity.COrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<COrderDetailEntity, String> {
}

