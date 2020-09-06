package se128.jupiter.orderservice.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import se128.jupiter.orderservice.dto.GoodsDetailDto;
import se128.jupiter.orderservice.dto.GoodsDto;
import se128.jupiter.orderservice.utils.msgutils.Msg;

@FeignClient(name="goods-service")
public interface GoodsFeign {
    @RequestMapping("/getGoodsDetailByDetailId/{detailId}")
    GoodsDetailDto getGoodsDetailByDetailId(@PathVariable Integer detailId);

    @RequestMapping("/getGoodsByGoodsId/{goodsId}")
    GoodsDto getGoodsByGoodsId(@PathVariable Integer goodsId);

    @PostMapping("/editGoods")
    Msg editGoods(@RequestBody GoodsDto goods);

    @RequestMapping("/updateGoodsCount/{goodsId}/{number}")
    GoodsDto updateGoodsCount(@PathVariable Integer goodsId, @PathVariable Integer number);
}
