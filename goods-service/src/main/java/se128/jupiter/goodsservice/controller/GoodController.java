package se128.jupiter.goodsservice.controller;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.goodsservice.dto.UserDto;
import se128.jupiter.goodsservice.entity.Auction;
import se128.jupiter.goodsservice.entity.CGoodEntity;
import se128.jupiter.goodsservice.entity.CGoodsDetail;
import se128.jupiter.goodsservice.msgutils.Msg;
import se128.jupiter.goodsservice.msgutils.MsgCode;
import se128.jupiter.goodsservice.msgutils.MsgUtil;
import se128.jupiter.goodsservice.service.GoodServiceImpl;
import se128.jupiter.goodsservice.service.SsoFeign;
import se128.jupiter.goodsservice.service.UserFeign;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private SsoFeign ssoFeign;
    @Autowired
    private UserFeign userFeign;

    @PutMapping("/addGoods")
    public Msg addGoods(@RequestBody CGoodEntity goods) {
        logger.info("addGoods" + goods);
        goods.setBuyCounter(0);
        goods.setViewCounter(0);
        CGoodEntity goods1 = goodService.addGoods(goods);
        JSONObject data = JSONObject.fromObject(goods1);
        return MsgUtil.makeMsg(MsgCode.ADD_SUCCESS, data);
    }

    @DeleteMapping(value = "/delete/{goodsId}")
    public Msg deleteGoodsByGoodsId(@PathVariable Integer goodsId) {
        logger.info("deleteGoodsWithGoodsId = " + goodsId);
        goodService.deleteGoodsByGoodsId(goodsId);
        return MsgUtil.makeMsg(MsgCode.DELETE_SUCCESS);
    }

    @GetMapping("/{goodsId}")
    public Msg getGood(@PathVariable Integer goodsId) {
        try {
//            Integer goodsId = Integer.valueOf(params.get(Constant.GOODS_ID));
            logger.info("getGoodsByGoodsId = " + goodsId);
            CGoodEntity goods = goodService.getGood(goodsId);
            if (goods.getGoodsType() < 0) {
                return MsgUtil.makeMsg(MsgCode.DATA_ERROR, "商品已下架");
            }
            JSONObject data = JSONObject.fromObject(goods);
            return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
        } catch (NumberFormatException e) {
            return MsgUtil.makeMsg(MsgCode.DATA_ERROR);
        } catch (NullPointerException e) {
            return MsgUtil.makeMsg(MsgCode.DATA_ERROR, "No such goodsId");
        }
    }

    @PostMapping
    public CGoodEntity saveGood(@RequestBody CGoodEntity cGoodEntity) {
        return goodService.saveGood(cGoodEntity);
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

    @GetMapping("/getRecommendGoods/{number}")
    public Msg getRecommendGoods(HttpServletRequest request, @PathVariable Integer number) {
        // 找accessToken
        String accessToken  = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    accessToken = cookie.getValue();
                }
            }
        }
        JSONObject user = ssoFeign.getKeyValue(accessToken);
        if (user == null) {
            logger.info("getRecommendGoodsInAll" + "number: " + number);
            List<CGoodEntity> goods = goodService.getRecommendGoodsInAll(number);
            JSONArray jsonArray = JSONArray.fromObject(goods);
            JSONObject data = new JSONObject();
            data.put("goods", jsonArray.toString());
            return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
        } else {
            Integer userId = user.getInt("userId");
            logger.info("getRecommendGoodsByUserId" + userId + "number" + number);

            // 获取user最喜欢的物品
            int maxBuy = -1;
            Integer maxNum = -1;
            UserDto userDto = userFeign.getUser(userId);
            if(maxNum < userDto.getBuy0()){
                maxBuy = 0;
                maxNum = userDto.getBuy0();
            }
            if(maxNum < userDto.getBuy1()){
                maxBuy = 1;
                maxNum = userDto.getBuy1();
            }
            if(maxNum < userDto.getBuy2()){
                maxBuy = 2;
                maxNum = userDto.getBuy2();
            }
            if(maxNum < userDto.getBuy3()){
                maxBuy = 3;
                maxNum = userDto.getBuy3();
            }
            if(maxNum < userDto.getBuy4()){
                maxBuy = 4;
                maxNum = userDto.getBuy4();
            }
            if(maxNum < userDto.getBuy5()){
                maxBuy = 5;
                maxNum = userDto.getBuy5();
            }

            List<CGoodEntity> goods = goodService.getRecommendGoodsByGoodsType(maxBuy, number);
            JSONArray jsonArray = JSONArray.fromObject(goods);
            JSONObject data = new JSONObject();
            data.put("goods", jsonArray.toString());
            return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
        }
    }

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
        auction.setGoods(goodService.getGood(goodsId));
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
        logger.info("deleteAuctionByAuctionId" + auctionId) ;
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
        try {
//            Integer AuctionId = Integer.valueOf(params.get(Constant.AUCTION_ID));
            logger.info("getAuctionByAuctionsId = " + AuctionId);
            Auction auction = goodService.getAuctionByAuctionId(AuctionId);
            if(auction == null){
                return MsgUtil.makeMsg(MsgCode.DATA_ERROR, "No such auctionId");
            }
            JSONObject data = JSONObject.fromObject(auction);
            return MsgUtil.makeMsg(MsgCode.DATA_SUCCESS, data);
        } catch (NumberFormatException e) {
            return MsgUtil.makeMsg(MsgCode.DATA_ERROR);
        }
    }

    @PostMapping("/updateAuction")
    public Msg updateAuction(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Integer AuctionId = Integer.valueOf(params.get("auctionId"));
        Double offer = Double.valueOf(params.get("offer"));
        // 找accessToken
        String accessToken  = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("accessToken".equals(cookie.getName())){
                    accessToken = cookie.getValue();
                }
            }
        }
        JSONObject user = ssoFeign.getKeyValue(accessToken);

        if("".equals(accessToken) || user == null){
            return MsgUtil.makeMsg(MsgCode.EDIT_ERROR);
        }

        Integer userId = user.getInt("userId");
        logger.info("updateAuction auctionsId = " + AuctionId+ " userId = " + userId);
        Auction auction = goodService.updateAuction(AuctionId, userId,offer);
        if(auction.getBestOffer().equals(offer)) {
            return MsgUtil.makeMsg(MsgCode.EDIT_SUCCESS);
        }
        return MsgUtil.makeMsg(MsgCode.EDIT_ERROR);
    }

    @PostMapping("/editAuction")
    public Msg editAuction(@RequestBody Map<String, String> params) {
        logger.info("editAuction");

        Integer auctionId = Integer.valueOf(params.get("auctionId"));
        Auction auction = goodService.getAuctionByAuctionId(auctionId);
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

    // 以下接口供其他微服务使用
    @RequestMapping("/getGoodsDetailByDetailId/{detailId}")
    public CGoodsDetail getGoodsDetailByDetailId(@PathVariable Integer detailId){
        return goodService.getGoodsDetailByDetailId(detailId);
    }

    @RequestMapping("/getGoodsByGoodsId/{goodsId}")
    public CGoodEntity getGoodsByGoodsId(@PathVariable Integer goodsId){
        return goodService.getGood(goodsId);
    }

    @RequestMapping("/updateGoodsCount/{goodsId}/{number}")
    public CGoodEntity updateGoodsCount(@PathVariable Integer goodsId, @PathVariable Integer number){
        return goodService.updateGoodsCount(goodsId, number);
    }
}
