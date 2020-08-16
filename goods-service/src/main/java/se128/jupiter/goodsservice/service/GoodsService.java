package se128.jupiter.goodsservice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import se128.jupiter.goodsservice.entity.Goods;

public interface GoodsService {
    Goods getGoodsByGoodsId(Integer goodsId);

    Goods editGoods(Goods goods);

    Goods addGoods(Goods goods);

    void deleteGoodsByGoodsId(Integer goodsId);

    List<Goods> getGoodsByGoodsType(Integer goodsType);

    List<Goods> getGoodsByName(String name);

    List<Goods> getGoodsByPageId(Integer pageId);

    Page<Goods> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType);

    void saveViewCounter();

    List<Goods> getPopularGoods(Integer number, Integer goodsType);

    List<Goods> getRecommendGoodsByUserId(Integer userId, Integer number);

    List<Goods> getRecommendGoodsInAll(Integer number);


    void deleteAuctionByAuctionId(Integer auctionId);

}
