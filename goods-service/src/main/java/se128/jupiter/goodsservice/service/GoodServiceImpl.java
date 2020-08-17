package se128.jupiter.goodsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se128.jupiter.goodsservice.entity.CGoodEntity;
import se128.jupiter.goodsservice.repository.GoodRepository;

import java.util.List;

@Service
public class GoodServiceImpl {
    //    @Override
//    public CGoodEntity getGoodsByGoodsId(Integer goodsId) {
//        return null;
//    }
//
//    @Override
//    public CGoodEntity editGoods(CGoodEntity CGoodEntity) {
//        return null;
//    }
//
//    @Override
//    public CGoodEntity addGoods(CGoodEntity CGoodEntity) {
//        return null;
//    }
//
//    @Override
//    public void deleteGoodsByGoodsId(Integer goodsId) {
//
//    }
//
//    @Override
//    public List<CGoodEntity> getGoodsByGoodsType(Integer goodsType) {
//        return null;
//    }
//
//    @Override
//    public List<CGoodEntity> getGoodsByName(String name) {
//        return null;
//    }
//
//    @Override
//    public List<CGoodEntity> getGoodsByPageId(Integer pageId) {
//        return null;
//    }
//
//    @Override
//    public Page<CGoodEntity> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType) {
//        return null;
//    }
//
//    @Override
//    public void saveViewCounter() {
//
//    }
//
//    @Override
//    public List<CGoodEntity> getPopularGoods(Integer number, Integer goodsType) {
//        return null;
//    }
//
//    @Override
//    public List<CGoodEntity> getRecommendGoodsByUserId(Integer userId, Integer number) {
//        return null;
//    }
//
//    @Override
//    public List<CGoodEntity> getRecommendGoodsInAll(Integer number) {
//        return null;
//    }
//
//    @Override
//    public void deleteAuctionByAuctionId(Integer auctionId) {
//    }
    @Autowired
    private GoodRepository goodRepository;

    public CGoodEntity getGood(String id) {
        if (!goodRepository.existsById(id)) {
            return null;
        }
        return goodRepository.findById(id).get();
    }

    public CGoodEntity saveGood(CGoodEntity cGoodEntity) {
        return goodRepository.save(cGoodEntity);
    }

}
