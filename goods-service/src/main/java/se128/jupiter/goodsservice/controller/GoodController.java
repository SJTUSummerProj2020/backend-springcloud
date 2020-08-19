package se128.jupiter.goodsservice.controller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.goodsservice.entity.Auction;
import se128.jupiter.goodsservice.entity.CGoodEntity;
import se128.jupiter.goodsservice.msgutils.Msg;
import se128.jupiter.goodsservice.msgutils.MsgCode;
import se128.jupiter.goodsservice.msgutils.MsgUtil;
import se128.jupiter.goodsservice.service.GoodServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
public class GoodController {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private GoodServiceImpl goodService;

    @GetMapping("{id}")
    public CGoodEntity getGood(@PathVariable Integer id) {
        return goodService.getGood(id);
    }

    @PostMapping
    public CGoodEntity saveGood(@RequestBody CGoodEntity cGoodEntity) {
        return goodService.saveGood(cGoodEntity);
    }

    @DeleteMapping("{id}")
    public CGoodEntity deleteGoodsByGoodsId(@PathVariable Integer id) {
        logger.info("deleteGoodsWithGoodsId = " + id);
        return goodService.deleteGoodsByGoodsId(id);
    }

    @GetMapping(value="/search/{name}")
    public Msg getGoodsByName(@PathVariable String name) {
        logger.info("getGoodsByName = " + name);
        List<CGoodEntity> goods = goodService.getGoodsByName(name);
        JSONObject data = new JSONObject();
        JSONArray goodsList = JSONArray.fromObject(goods);
        data.put("goods", goodsList.toString());
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

    @GetMapping("/getAllGoods/{pageId}/{pageSize}/{goodsType}")
    public Msg getAllGoods(@PathVariable("pageId") Integer pageId,
                           @PathVariable("pageSize") Integer pageSize,@PathVariable("goodsType") Integer goodsType) {
        logger.info("getAllGoods");

        Page<CGoodEntity> goodsPage = goodService.getAllGoods(pageId, pageSize, goodsType);
        JSONObject data = new JSONObject();
        data.put("totalNum", goodsPage.getTotalElements());
        JSONArray goods = JSONArray.fromObject(goodsPage.getContent());
        data.put("goods", goods.toString());
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

    @GetMapping("/getPopularGoods/{number}")
    public Msg getPopularGoods(@PathVariable Integer number) {
        logger.info("getPopularGoods");
//        Integer number = Integer.valueOf(params.get(Constant.NUMBER));
        JSONObject data = new JSONObject();
        for (int goodsType = -1; goodsType < 5; goodsType++) {
            List<CGoodEntity> goods = goodService.getPopularGoods(number, goodsType);
            JSONArray goodsList = JSONArray.fromObject(goods);
            if (goodsType == -1) {
                data.put("itemAll", goodsList.toString());
            } else {
                data.put("item" + goodsType, goodsList.toString());
            }
        }
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }
//    @GetMapping("/getRecommendGoods/{number}")
//    public Msg getRecommendGoods(@PathVariable Integer number) {
////        Integer number = Integer.valueOf(params.get(Constant.NUMBER));
//        JSONObject user = SessionUtil.getAuth();
//        if (user == null) {
//            logger.info("getRecommendGoodsInAll" + "number: " + number);
//            List<Goods> goods = goodsService.getRecommendGoodsInAll(number);
//            JSONArray jsonArray = JSONArray.fromObject(goods);
//            JSONObject data = new JSONObject();
//            data.put("goods", jsonArray.toString());
//            return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
//        } else {
//            Integer userId = user.getInt(Constant.USER_ID);
//            logger.info("getRecommendGoodsByUserId" + userId + "number" + number);
//            List<Goods> goods = goodsService.getRecommendGoodsByUserId(userId, number);
//            JSONArray jsonArray = JSONArray.fromObject(goods);
//            JSONObject data = new JSONObject();
//            data.put("goods", jsonArray.toString());
//            return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
//        }
//    }

    @PostMapping("/editGoods")
    public Msg editGoods(@RequestBody CGoodEntity goods) {
        logger.info("editGoods");
        CGoodEntity goods1 = goodService.editGoods(goods);
        JSONObject data = JSONObject.fromObject(goods1);
        return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS, data);
    }

    @PutMapping("/addAuction")
    public Msg addAuction(@RequestBody Map<String, String> params) {
        logger.info("addAuction");
        Auction auction = new Auction();
        Integer detailId = Integer.valueOf(params.get("detailId"));
        Integer goodsId = Integer.valueOf(params.get("goodsId"));
        Double startingPrice = Double.valueOf(params.get("startingPrice"));
        Double addingPrice = Double.valueOf(params.get("addingPrice"));
        String startTime = params.get("startTime");
        Integer duration = Integer.valueOf(params.get("duration"));
        auction.setStartingPrice(startingPrice);
        auction.setAddingPrice(addingPrice);
        auction.setStartTime(startTime);
        auction.setDuration(duration);
        auction.setUserId(1);
        auction.setBestOffer(0.0);
        Auction auction1 = goodService.addAuction(auction, goodsId, detailId);
        JSONObject data = JSONObject.fromObject(auction1);
        return MsgUtil.makeMsg(MsgCode.ADD_SUCCESS, data);
    }

    @DeleteMapping("/deleteAuctionByAuctionId/{auctionId}")
    public Msg deleteAuctionByAuctionId(@PathVariable Integer auctionId) {
        logger.info("deleteAuctionByAuctionId");
        goodService.deleteAuctionByAuctionId(auctionId);
        return MsgUtil.makeMsg(MsgCode.DELETE_SUCCESS);
    }

    @GetMapping("/getAllAuctions")
    public Msg getAllAuctions() throws ParseException {
        logger.info("getAllAuctions");
        List<Auction> auctions = goodService.getAllAuctions();
        Date now = new Date();
        List<Auction> ret = new LinkedList<>();
        for (Auction auction : auctions)
        {
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = simpleDateFormat.parse(auction.getStartTime());
            Date end = new Date(start.getTime()+1000*auction.getDuration());
            if(now.before(end))
            {
                ret.add(auction);
            }
        }
        JSONObject data = new JSONObject();
        JSONArray auctionList = JSONArray.fromObject(ret);
        data.put("auctions", auctionList.toString());
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

    @GetMapping("/getAuctionByAuctionId/{AuctionId}")
    public Msg getAuctionByAuctionId(@PathVariable Integer AuctionId) {
        logger.info("getAuctionByAuctionsId = " + AuctionId);
        Auction auction = goodService.getAuctionByAuctionId(AuctionId);
        if(auction == null){
            return MsgUtil.makeMsg(MsgCode.DATA_ERROR, "No such auctionId");
        }
        JSONObject data = JSONObject.fromObject(auction);
        return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
    }

//    @PostMapping("/updateAuction")
//    public Msg updateAuction(@RequestBody Map<String, String> params) {
//        Integer AuctionId = Integer.valueOf(params.get(Constant.AUCTION_ID));
//        Double offer = Double.valueOf(params.get(Constant.OFFER));
//        JSONObject user = SessionUtil.getAuth();
//        if(user == null){
//            return MsgUtil.makeMsg(MsgCode.EDIT_ERROR);
//        }
//        Integer userId = user.getInt(Constant.USER_ID);
//        logger.info("updateAuction auctionsId = " + AuctionId+ " userId = " + userId);
//        Auction auction = goodsService.updateAuction(AuctionId,userId,offer);
//        if(auction.getBestOffer().equals(offer)) {
//            return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS);
//        }
//        return MsgUtil.makeMsg(MsgCode.EDIT_ERROR);
//    }

    @PostMapping("/editAuction")
    public Msg editAuction(@RequestBody Map<String, String> params) {
        logger.info("editAuction");
        Auction auction = new Auction();
        Integer auctionId = Integer.valueOf(params.get("auctionId"));
        Integer detailId = Integer.valueOf(params.get("detailId"));
        Integer goodsId = Integer.valueOf(params.get("goodsId"));
        Double startingPrice = Double.valueOf(params.get("startingPrice"));
        Double addingPrice = Double.valueOf(params.get("addingPrice"));
        String startTime = params.get("startTime");
        Integer duration = Integer.valueOf(params.get("duration"));
        auction.setAuctionId(auctionId);
        auction.setStartingPrice(startingPrice);
        auction.setAddingPrice(addingPrice);
        auction.setStartTime(startTime);
        auction.setDuration(duration);
        auction.setUserId(1);
        auction.setBestOffer(0.0);
        Auction auction1 = goodService.editAuction(auction,detailId,goodsId);
        JSONObject data = JSONObject.fromObject(auction1);
        return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS, data);
    }


}
