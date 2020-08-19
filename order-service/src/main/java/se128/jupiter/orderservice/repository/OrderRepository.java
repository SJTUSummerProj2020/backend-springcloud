package se128.jupiter.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se128.jupiter.orderservice.entity.COrderEntity;

import java.util.List;

public interface OrderRepository extends JpaRepository<COrderEntity, Integer> {
    List<COrderEntity> findByUserId(Integer userId);
}
