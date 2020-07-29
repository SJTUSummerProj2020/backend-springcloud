package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "goods")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Goods {

    @Id
    @Column(name = "goods_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsId;

    private String name;

    private String startTime;

    private String endTime;

    private String address;
    private String website;

    private Integer goodsType;

    private String image;

    private Integer viewCounter;

    private Integer buyCounter;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id")
    private List<GoodsDetail> goodsDetails;

    @Transient
    private String detail;

}
