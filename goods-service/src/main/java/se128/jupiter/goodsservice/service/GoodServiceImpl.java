package se128.jupiter.goodsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se128.jupiter.goodsservice.dao.GoodsDaoImpl;
import se128.jupiter.goodsservice.entity.Auction;
import se128.jupiter.goodsservice.entity.CGoodEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class GoodServiceImpl {

    private final GoodsDaoImpl goodsDao;

    private HashMap<Integer,Integer> goodsViewCounter;

    @Autowired
    public GoodServiceImpl(GoodsDaoImpl goodsDao) {
        this.goodsDao = goodsDao;
        this.goodsViewCounter = new HashMap<Integer,Integer>();
    }
    public CGoodEntity getGood(Integer id) {
        CGoodEntity goods =  goodsDao.getGoodsByGoodsId(id);
        if(goods != null)
        {
            this.goodsViewCounter.merge(id, 1, Integer::sum);
        }
        return goods;
    }

    public CGoodEntity saveGood(CGoodEntity cGoodEntity) {
        return goodsDao.saveGoods(cGoodEntity);
    }

    public CGoodEntity editGoods(CGoodEntity goods) {
        return goodsDao.editGoods(goods);
    }

    public CGoodEntity deleteGoodsByGoodsId(Integer id) {
        CGoodEntity cGoodEntity = goodsDao.getGoodsByGoodsId(id);
        cGoodEntity.setGoodsType(-cGoodEntity.getGoodsType());
        return goodsDao.saveGoods(cGoodEntity);
    }

    public List<CGoodEntity> getGoodsByGoodsType(Integer goodsType) {
        return goodsDao.getGoodsByGoodsType(goodsType);
    }

    public List<CGoodEntity> getGoodsByName(String name) {
        String [] names = name.split("\\s+");
        List<CGoodEntity> goods = new LinkedList<>();

        for(int i = 0;i<names.length; i++)
        {
            List<CGoodEntity> someGoods = goodsDao.getGoodsByName(names[i]);
            if(i == 0)
            {
                goods.addAll(someGoods);
            }
            else
            {
                goods.retainAll(someGoods);
            }
        }
//        HashSet<Goods> tmp = new HashSet<Goods>(goods);
//        goods.clear();
//        goods.addAll(tmp);
        return goods;
    }

    public List<CGoodEntity> getGoodsByPageId(Integer pageId) {
        return goodsDao.getGoodsByPage(pageId);
    }

    public Page<CGoodEntity> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType) {
        return goodsDao.getAllGoods(pageId,pageSize,goodsType);
    }

    public void saveViewCounter() {
        goodsDao.saveViewCounter(this.goodsViewCounter);
    }

    public List<CGoodEntity> getPopularGoods(Integer number, Integer goodsType) {
        return goodsDao.getPopularGoods(number,goodsType);
    }

    public void deleteAuctionByAuctionId(Integer auctionId) {

    }

    public List<Auction> getAllAuctions() {
        return null;
    }
    public Auction getAuctionByAuctionId(Integer auctionId) {
        return null;
    }
    public Auction updateAuction(Integer auctionId, Integer userId, Double offer) {
        return null;
    }
    public Auction editAuction(Auction auction, Integer detailId, Integer goodsId) {
        return null;
    }
    public Auction addAuction(Auction auction, Integer goodsId, Integer detailId) {
        return null;
    }
}
