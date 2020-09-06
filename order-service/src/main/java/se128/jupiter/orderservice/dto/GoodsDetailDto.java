package se128.jupiter.orderservice.dto;

import javax.persistence.*;

public class GoodsDetailDto {
    private Integer detailId;
    private Integer goodsId;
    private Double price;
    private Integer surplus;
    private String time;
    private String ticketType;

    public Integer getDetailId()
    {
        return detailId;
    }
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSurplus() {
        return surplus;
    }
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
