package se128.jupiter.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se128.jupiter.orderservice.dto.GoodsDetailDto;
import se128.jupiter.orderservice.dto.GoodsDto;
import se128.jupiter.orderservice.dto.UserDto;
import se128.jupiter.orderservice.entity.COrderEntity;
import se128.jupiter.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GoodsFeign goodsFeign;
    @Autowired
    private UserFeign userFeign;

    public COrderEntity getOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            return null;
        }
        return orderRepository.findById(id).get();
    }

    public COrderEntity saveOrder(COrderEntity COrderEntity) {
        COrderEntity.setSourceId(String.valueOf(System.currentTimeMillis()));
        return orderRepository.save(COrderEntity);
    }

    public COrderEntity addOrder(COrderEntity order,Integer detailId) {
        GoodsDetailDto goodsDetail = goodsFeign.getGoodsDetailByDetailId(detailId);
        if(goodsDetail != null && goodsDetail.getSurplus()==1){    // 有库存
            order.setDetailId(detailId);
            Integer goodsId = goodsDetail.getGoodsId();
            GoodsDto goods = goodsFeign.getGoodsByGoodsId(goodsId);
            Integer goodsType = goods.getGoodsType();
            goodsFeign.updateGoodsCount(goodsId, order.getNumber());
            order.setGoodsId(goodsId);
            Double price = goodsDetail.getPrice();
            Double totalPrice = price * order.getNumber();
            order.setPrice(totalPrice);
            userFeign.addBuy(order.getUserId(), goodsType);
            return orderRepository.saveAndFlush(order);
        }
        else {
            return null;
        }
    }

    public List<COrderEntity> getOrderListByUser(Integer userId){
        return orderRepository.findByUserId(userId);
    }

    public List<COrderEntity> getAllOrder() {
        return orderRepository.findAll();
    }
}
