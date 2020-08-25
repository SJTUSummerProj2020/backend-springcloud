package se128.jupiter.goodsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se128.jupiter.goodsservice.dao.AuctionDaoImpl;
import se128.jupiter.goodsservice.dao.GoodsDaoImpl;
import se128.jupiter.goodsservice.entity.Auction;
import se128.jupiter.goodsservice.entity.CGoodEntity;
import se128.jupiter.goodsservice.entity.CGoodsDetail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class GoodServiceImpl {

    private final GoodsDaoImpl goodsDao;

    private final AuctionDaoImpl auctionDao;

    private HashMap<Integer,Integer> goodsViewCounter;

    @Autowired
    public GoodServiceImpl(GoodsDaoImpl goodsDao, AuctionDaoImpl auctionDao) {
        this.goodsDao = goodsDao;
        this.auctionDao = auctionDao;
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

    public CGoodEntity editGoods(CGoodEntity goods) {
        return goodsDao.editGoods(goods);
    }

    public CGoodEntity saveGood(CGoodEntity cGoodEntity) {
        return goodsDao.saveGoods(cGoodEntity);
    }

    public CGoodEntity addGoods(CGoodEntity goods) {
        return goodsDao.addGoods(goods);
    }
    public void deleteGoodsByGoodsId(Integer id) {
        goodsDao.deleteGoodsByGoodsId(id);
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

    public Page<CGoodEntity> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType) {
        return goodsDao.getAllGoods(pageId,pageSize,goodsType);
    }

    public void saveViewCounter() {
        goodsDao.saveViewCounter(this.goodsViewCounter);
    }

    public List<CGoodEntity> getPopularGoods(Integer number, Integer goodsType) {
        return goodsDao.getPopularGoods(number,goodsType);
    }

    public List<CGoodEntity> getRecommendGoodsByGoodsType(Integer goodsType, Integer number) {
        return goodsDao.getRecommendGoodsByGoodsType(goodsType,number);
    }

    public List<CGoodEntity> getRecommendGoodsInAll(Integer number) {
        return goodsDao.getRecommendGoodsInAll(number);
    }

    public void deleteAuctionByAuctionId(Integer auctionId) {
        auctionDao.deleteAuctionByAuctionId(auctionId);
    }

    public List<Auction> getAllAuctions() {
        return auctionDao.getAllAuctions();
    }
    public Auction getAuctionByAuctionId(Integer auctionId) {
        return auctionDao.getAuctionByAuctionId(auctionId);
    }
    public Auction updateAuction(Integer auctionId, Integer userId, Double offer) {
        Auction auction = auctionDao.getAuctionByAuctionId(auctionId);
        if(auction.getBestOffer()<offer)
        {
            auction.setBestOffer(offer);
            auction.setUserId(userId);
        }
        return auctionDao.saveAuction(auction);
    }
    public Auction editAuction(Auction auction, Integer detailId, Integer goodsId) {
        CGoodEntity goods = goodsDao.getGoodsByGoodsId(goodsId);
        CGoodsDetail detail = goodsDao.getGoodsDetailByDetailId(detailId);
        auction.setGoods(goods);
        auction.setGoodsDetail(detail);
        return auctionDao.editAuction(auction);
    }
    public Auction addAuction(Auction auction, Integer goodsId, Integer detailId) {
        CGoodEntity goods = goodsDao.getGoodsByGoodsId(goodsId);
        CGoodsDetail detail = goodsDao.getGoodsDetailByDetailId(detailId);
        auction.setGoods(goods);
        auction.setGoodsDetail(detail);
        return auctionDao.addAuction(auction);
    }


}
