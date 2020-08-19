package se128.jupiter.usercenter.dto;

import java.sql.Timestamp;

public class GoodsDetailDto {
    private Integer detailId;
    private Integer goodsId;
    private Double price;
    private Integer surplus;
    private Timestamp time;
    private String ticketType;

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getSurplus() {
        return surplus;
    }

    public String getTicketType() {
        return ticketType;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }
}
