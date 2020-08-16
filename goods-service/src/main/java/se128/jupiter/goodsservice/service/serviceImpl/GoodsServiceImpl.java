package se128.jupiter.goodsservice.service.serviceImpl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se128.jupiter.goodsservice.entity.Goods;
import se128.jupiter.goodsservice.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Override
    public Goods getGoodsByGoodsId(Integer goodsId) {
        return null;
    }

    @Override
    public Goods editGoods(Goods goods) {
        return null;
    }

    @Override
    public Goods addGoods(Goods goods) {
        return null;
    }

    @Override
    public void deleteGoodsByGoodsId(Integer goodsId) {

    }

    @Override
    public List<Goods> getGoodsByGoodsType(Integer goodsType) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByName(String name) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByPageId(Integer pageId) {
        return null;
    }

    @Override
    public Page<Goods> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType) {
        return null;
    }

    @Override
    public void saveViewCounter() {

    }

    @Override
    public List<Goods> getPopularGoods(Integer number, Integer goodsType) {
        return null;
    }

    @Override
    public List<Goods> getRecommendGoodsByUserId(Integer userId, Integer number) {
        return null;
    }

    @Override
    public List<Goods> getRecommendGoodsInAll(Integer number) {
        return null;
    }

    @Override
    public void deleteAuctionByAuctionId(Integer auctionId) {
    }
}
