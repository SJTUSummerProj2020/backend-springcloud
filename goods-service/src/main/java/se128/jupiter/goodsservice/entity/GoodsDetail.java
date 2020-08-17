//package se128.jupiter.goodsservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "goodsdetail")
//@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
//public class GoodsDetail {
//    @Id
//    @Column(name = "detail_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer detailId;
//
//    @Column(name = "goods_id")
//    private Integer goodsId;
//
//    private Double price;
//
//    private Integer surplus;
//
//    private String time;
//
//    private String ticketType;
//
//}
