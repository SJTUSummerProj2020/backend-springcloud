package se128.jupiter.goodsservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se128.jupiter.goodsservice.entity.CDetail;
import se128.jupiter.goodsservice.entity.CGoodEntity;
import se128.jupiter.goodsservice.entity.CGoodsDetail;
import se128.jupiter.goodsservice.repository.DetailRepository;
import se128.jupiter.goodsservice.repository.GoodRepository;
import se128.jupiter.goodsservice.repository.GoodsDetailRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GoodsDaoImpl {

    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private GoodsDetailRepository goodsDetailRepository;
    @Autowired
    private  DetailRepository detailRepository;

    public CGoodEntity getGoodsByGoodsId(Integer goodsId) {
        CDetail detail;
        CGoodEntity goods = goodRepository.getGoodsByGoodsId(goodsId);
        try{
            detail = detailRepository.getDetailByGoodsId(goodsId);
            goods.setDetail(detail.getDetail());
        }catch(Exception e){
            return null;
        }
        return goods;
    }

    public CGoodsDetail getGoodsDetailByDetailId(Integer detailId) {
        CGoodsDetail detail;
        try{
            detail = goodsDetailRepository.getGoodsDetailByDetailId(detailId);
        }catch(Exception e){
            detail = null;
        }
        return detail;
    }

    public CGoodEntity editGoods(CGoodEntity cGoodEntity) {
        CGoodEntity goods1 = goodRepository.getGoodsByGoodsId(cGoodEntity.getGoodsId());
        if(goods1 == null)
        {
            return null;
        }
        goods1.setName(cGoodEntity.getName());
        goods1.setStartTime(cGoodEntity.getStartTime());
        goods1.setEndTime(cGoodEntity.getEndTime());
        goods1.setAddress(cGoodEntity.getAddress());
        goods1.setWebsite(cGoodEntity.getWebsite());
        goods1.setGoodsType(cGoodEntity.getGoodsType());
        goods1.setImage(cGoodEntity.getImage());
        goods1.setViewCounter(cGoodEntity.getViewCounter());
        goods1.setBuyCounter(cGoodEntity.getBuyCounter());
        return goodRepository.saveAndFlush(goods1);
    }

    public CGoodEntity addGoods(CGoodEntity cGoodEntity) {
        //        Integer goodsId = goods1.getGoodsId();
//        List<CGoodsDetail> goodsDetails = goods1.getGoodsDetails();
//        for(CGoodsDetail item : goodsDetails)
//        {
//            item.setGoodsId(goodsId);
//        }
//        goodsDetailRepository.saveAll(goodsDetails);
        return goodRepository.save(cGoodEntity);
    }

    public void deleteGoodsByGoodsId(Integer goodsId) {
        try{
            CGoodEntity goods = goodRepository.getGoodsByGoodsId(goodsId);
            goods.setGoodsType(-1);
            List<CGoodsDetail> goodsDetails = goods.getGoodsDetails();
            goodRepository.saveAndFlush(goods);
            for(CGoodsDetail item : goodsDetails) {
                item.setSurplus(-1);
            }
            goodsDetailRepository.saveAll(goodsDetails);

        }
        catch (Exception e){
            System.out.println("Can't delete");
        }
    }

    public List<CGoodEntity> getGoodsByGoodsType(Integer goodsType) {
        return goodRepository.getGoodsByGoodsType(goodsType);
    }

    public List<CGoodEntity> getGoodsByName(String name) {
        return goodRepository.findAllByNameContains(name);
    }

    public List<CGoodEntity> getGoodsByPage(Integer pageId) {
        return goodRepository.getGoodsByPageId(pageId);
    }

    public Page<CGoodEntity> getAllGoods(Integer pageId, Integer pageSize, Integer goodsType) {
        PageRequest pageRequest = PageRequest.of(pageId,pageSize);
        if(goodsType==-1)
        {
            try {
                return goodRepository.findAll(pageRequest);
            }
            catch (Exception e)
            {
                System.out.println("error");
                return null;
            }
        }
        else
        {
            return goodRepository.findByGoodsType(goodsType,pageRequest);
        }
    }

    public void saveViewCounter(HashMap<Integer, Integer> goodsViewCounter) {
        for(Map.Entry<Integer, Integer> entry: goodsViewCounter.entrySet())
        {
            CGoodEntity goods = goodRepository.getGoodsByGoodsId(entry.getKey());
            goods.setViewCounter(goods.getViewCounter()+entry.getValue());
            goodRepository.save(goods);
        }
        goodRepository.flush();
    }

    public List<CGoodEntity> getPopularGoods(Integer number, Integer goodsType) {
        if(goodsType==-1)
        {
            return goodRepository.getPopularGoodsInAll(number);
        }
        else {
            return goodRepository.getPopularGoods(number,goodsType);
        }
    }

    public List<CGoodEntity> getRecommendGoodsByGoodsType(Integer favor, Integer number) {
        return goodRepository.getRecommendGoodsByGoodsType(favor,number);
    }

    public List<CGoodEntity> getRecommendGoodsInAll(Integer number) {
        return goodRepository.getRecommendGoodsInAll(number);
    }

    public CGoodEntity saveGoods(CGoodEntity cGoodEntity) {
        return goodRepository.save(cGoodEntity);
    }
}
