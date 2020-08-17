//package se128.jupiter.goodsservice.dao;
//
//import org.springframework.data.domain.Page;
//import se128.jupiter.goodsservice.entity.CGoodEntity;
//import se128.jupiter.goodsservice.entity.GoodsDetail;
//
//import java.util.HashMap;
//import java.util.List;
//
//public interface GoodsDao {
//    CGoodEntity getGoodsByGoodsId(Integer goodsId);
//
//    CGoodEntity editGoods(CGoodEntity CGoodEntity);
//
//    CGoodEntity addGoods(CGoodEntity CGoodEntity);
//
//    void deleteGoodsByGoodsId(Integer goodsId);
//
//    List<CGoodEntity> getGoodsByGoodsType(Integer goodsType);
//
//    List<CGoodEntity> getGoodsByName(String name);
//
//    List<CGoodEntity> getGoodsByPage(Integer pageId);
//
//    Page<CGoodEntity> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType);
//
//    void saveViewCounter(HashMap<Integer, Integer> goodsViewCounter);
//
//    List<CGoodEntity> getPopularGoods(Integer number, Integer goodsType);
//
//    List<CGoodEntity> getRecommendGoodsByGoodsType(Integer favor, Integer number);
//
//    List<CGoodEntity> getRecommendGoodsInAll(Integer number);
//
//    GoodsDetail getGoodsDetailByDetailId(Integer detailId);
//}
