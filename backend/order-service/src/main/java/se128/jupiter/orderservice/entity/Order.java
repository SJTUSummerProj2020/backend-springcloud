package se128.jupiter.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orderlist")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Basic
    @Column(name = "user_id")
    private Integer userId;

    @Basic
    @Column(name="detail_id")
    private Integer DetailId;

    @Basic
    @Column(name = "goods_id")
    private Integer goodsId;

    private Integer sourceId;
    private Integer number;

    private Double price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String time;

}
