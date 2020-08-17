package se128.jupiter.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se128.jupiter.orderservice.entity.COrderEntity;
import se128.jupiter.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;

    //    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }
//
//    @Override
//    public COrderEntity addOrder(COrderEntity COrderEntity) {
//        return orderRepository.save(COrderEntity);
//    }
//
//    @Override
//    public List<COrderEntity> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    @Override
//    public COrderEntity getOrderById(Integer id) {
//        Optional<COrderEntity> order = orderRepository.findById(id);
//        return order.orElse(null);
//    }
    public COrderEntity getOrder(String id) {
        if (!orderRepository.existsById(id)) {
            return null;
        }
        return orderRepository.findById(id).get();
    }

    public COrderEntity saveOrder(COrderEntity COrderEntity) {
        COrderEntity.setOrderNo(String.valueOf(System.currentTimeMillis()));
        return orderRepository.save(COrderEntity);
    }

    public List<COrderEntity> getOrderListByUser(String userId){
        return orderRepository.findByCreateByOrderByCreateDate(userId);
    }

}
