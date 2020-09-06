package se128.jupiter.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "orderlist")
@EntityListeners(AuditingEntityListener.class)
public class COrderEntity {
    private Integer orderId;
    private Integer userId;
    private Integer detailId;
    private Integer goodsId;
    private String sourceId = "DAMAI1010232108810";
    private String orderStatus = "0";
    private Integer number;
    private Double price;
    private Timestamp time;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "detail_id", nullable = false)
    public Integer getDetailId() {
        return detailId;
    }
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    @Basic
    @Column(name = "goods_id", nullable = false)
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "source_id", nullable = false)
    public String getSourceId() {
        return sourceId;
    }
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Basic
    @Column(name = "order_status", nullable = false)
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "time", nullable = false)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
