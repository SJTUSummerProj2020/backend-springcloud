package se128.jupiter.goodsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import se128.jupiter.goodsservice.entity.CGoodsDetail;

import javax.transaction.Transactional;

public interface GoodsDetailRepository extends JpaRepository<CGoodsDetail,Integer> {

    @Transactional
    @Modifying
    @Query(value ="delete from GoodsDetail where goods_id = ?1", nativeQuery = true)
    void deleteByGoodsId(Integer goodsId);


    CGoodsDetail getGoodsDetailByDetailId(Integer detailId);
}
