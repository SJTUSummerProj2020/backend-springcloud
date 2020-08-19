package se128.jupiter.goodsservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "goodsdetail")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class CGoodsDetail {

    private Integer detailId;
    private Integer goodsId;
    private Double price;
    private Integer surplus;
    private String time;
    private String ticketType;

    @Id
    @Column(name = "detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getDetailId()
    {
        return detailId;
    }
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    @Basic
    @Column(name = "goods_id", nullable = true)
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "price", nullable = true)
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "surplus", nullable = true)
    public Integer getSurplus() {
        return surplus;
    }
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "ticket_type", nullable = true)
    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
