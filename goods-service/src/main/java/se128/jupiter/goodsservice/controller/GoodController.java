package se128.jupiter.goodsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se128.jupiter.goodsservice.entity.CGoodEntity;
import se128.jupiter.goodsservice.service.GoodServiceImpl;


@RestController
public class GoodController {
    @Autowired
    private GoodServiceImpl goodService;

    @GetMapping("{id}")
    public CGoodEntity getGood(@PathVariable String id) {
        return goodService.getGood(id);
    }

    @PostMapping
    public CGoodEntity saveGood(@RequestBody CGoodEntity cGoodEntity) {
        return goodService.saveGood(cGoodEntity);
    }

}
