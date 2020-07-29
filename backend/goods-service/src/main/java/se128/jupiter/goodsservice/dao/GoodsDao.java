package se128.jupiter.goodsservice.dao;

import entity.Goods;
import entity.GoodsDetail;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface GoodsDao {
    Goods getGoodsByGoodsId(Integer goodsId);

    Goods editGoods(Goods goods);

    Goods addGoods(Goods goods);

    void deleteGoodsByGoodsId(Integer goodsId);

    List<Goods> getGoodsByGoodsType(Integer goodsType);

    List<Goods> getGoodsByName(String name);

    List<Goods> getGoodsByPage(Integer pageId);

    Page<Goods> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType);

    void saveViewCounter(HashMap<Integer, Integer> goodsViewCounter);

    List<Goods> getPopularGoods(Integer number, Integer goodsType);

    List<Goods> getRecommendGoodsByGoodsType(Integer favor, Integer number);

    List<Goods> getRecommendGoodsInAll(Integer number);

    GoodsDetail getGoodsDetailByDetailId(Integer detailId);
}
