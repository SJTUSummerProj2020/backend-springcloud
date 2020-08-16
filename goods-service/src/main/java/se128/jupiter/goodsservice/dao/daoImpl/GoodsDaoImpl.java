package se128.jupiter.goodsservice.dao.daoImpl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import se128.jupiter.goodsservice.dao.GoodsDao;
import se128.jupiter.goodsservice.entity.Goods;
import se128.jupiter.goodsservice.entity.GoodsDetail;

import java.util.HashMap;
import java.util.List;

@Repository
public class GoodsDaoImpl implements GoodsDao {
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
    public List<Goods> getGoodsByPage(Integer pageId) {
        return null;
    }

    @Override
    public Page<Goods> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType) {
        return null;
    }

    @Override
    public void saveViewCounter(HashMap<Integer, Integer> goodsViewCounter) {

    }

    @Override
    public List<Goods> getPopularGoods(Integer number, Integer goodsType) {
        return null;
    }

    @Override
    public List<Goods> getRecommendGoodsByGoodsType(Integer favor, Integer number) {
        return null;
    }

    @Override
    public List<Goods> getRecommendGoodsInAll(Integer number) {
        return null;
    }

    @Override
    public GoodsDetail getGoodsDetailByDetailId(Integer detailId) {
        return null;
    }
}
