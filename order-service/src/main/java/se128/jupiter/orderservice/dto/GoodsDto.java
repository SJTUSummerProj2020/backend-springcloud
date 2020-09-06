package se128.jupiter.orderservice.dto;

import javax.persistence.*;
import java.util.List;

public class GoodsDto {
    private Integer goodsId;
    private String name;
    private String startTime;
    private String endTime;
    private String address;
    private String website;
    private Integer goodsType;
    private String image;
    private Integer viewCounter = 0;
    private Integer buyCounter = 0;
    private List<GoodsDetailDto> goodsDetails;
    private String detail;

    public Integer getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId)
    {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public Integer getViewCounter() {
        return viewCounter;
    }
    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }


    public Integer getBuyCounter() {
        return buyCounter;
    }
    public void setBuyCounter(Integer buyCounter) {
        this.buyCounter = buyCounter;
    }

    public List<GoodsDetailDto> getGoodsDetails()
    {
        return goodsDetails;
    }
    public void setGoodsDetails(List<GoodsDetailDto> goodsDetails)
    {
        this.goodsDetails= goodsDetails;
    }

}
