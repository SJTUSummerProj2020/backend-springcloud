package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "auction")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Auction {

    @Id
    @Column(name = "auction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer auctionId;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private GoodsDetail goodsDetail;

    @OneToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private Integer userId;

    private Double startingPrice;

    private Double addingPrice;

    private Double bestOffer;

    private String startTime;

    private Integer duration;
}
