package se128.jupiter.goodsservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se128.jupiter.goodsservice.entity.CGoodEntity;

import java.util.List;

public interface GoodRepository extends JpaRepository<CGoodEntity, String> {

    @Query(value = "select g from CGoodEntity g where g.goodsType >= 0 ")
    List<CGoodEntity> getAllGoods();

    CGoodEntity getGoodsByGoodsId(Integer goodsId);

    List<CGoodEntity> getGoodsByGoodsType(Integer goodsType);

    @Query(value = "from CGoodEntity where name like %?1%")
    List<CGoodEntity> getGoodsByName(String name);

    List<CGoodEntity> findAllByNameLike(String name);

    List<CGoodEntity> findAllByNameContains(String name);

    @Query(nativeQuery = true, value = "select * from goods order by goods_id limit 3")
    List<CGoodEntity> getGoodsByPageId(Integer pageId);

    @Query(value = "SELECT * FROM goods WHERE goods_type = ?1",
            countQuery = "SELECT count(*) FROM goods WHERE goods_type = ?1",
            nativeQuery = true)
    Page<CGoodEntity> findByGoodsType(Integer goodsType, PageRequest pageable);

    @Query(nativeQuery = true,
            value = "select * from goods where goods_type =?2 order by view_counter desc limit ?1")
    List<CGoodEntity> getPopularGoods(Integer number, Integer goodsType);

    @Query(nativeQuery = true,
            value = "select * from goods where goods_type >= 0 order by view_counter desc limit ?1")
    List<CGoodEntity> getPopularGoodsInAll(Integer number);

    @Query(nativeQuery = true,
            value = "select * from goods where goods_type =?1 order by buy_counter desc limit ?2")
    List<CGoodEntity> getRecommendGoodsByGoodsType(Integer goodsType, Integer number);

    @Query(nativeQuery = true,
            value = "select * from goods where goods_type >= 0 order by buy_counter desc limit ?1")
    List<CGoodEntity> getRecommendGoodsInAll(Integer number);
}
