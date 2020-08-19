package se128.jupiter.usercenter.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class GoodsDto {
    private Integer goodsId;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String address;
    private String website;
    private Integer goodsType;
    private String image;
    private Integer viewCounter;
    private Integer buyCounter;
    private List<GoodsDetailDto> goodsDetails;
    private String detail;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public List<GoodsDetailDto> getGoodsDetails() {
        return goodsDetails;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public void setGoodsDetails(List<GoodsDetailDto> goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Integer getBuyCounter() {
        return buyCounter;
    }

    public Integer getViewCounter() {
        return viewCounter;
    }

    public void setBuyCounter(Integer buyCounter) {
        this.buyCounter = buyCounter;
    }

    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
