package se128.jupiter.goodsservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "c_good")
@EntityListeners(AuditingEntityListener.class)
public class CGoodEntity {
    //
//    @Id
//    @Column(name = "goods_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer goodsId;
//
//    private String name;
//
//    private String startTime;
//
//    private String endTime;
//
//    private String address;
//    private String website;
//
//    private Integer goodsType;
//
//    private String image;
//
//    private Integer viewCounter;
//
//    private Integer buyCounter;
//
//    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "goods_id")
//    private List<GoodsDetail> goodsDetails;
//
//    @Transient
//    private String detail;
    private String id;
    private String goodName;
    private Integer goodStock;
    private double goodPrice;
    private Timestamp createDate;
    private String createBy = "sys";
    private Timestamp updateDate;
    private String updateBy = "sys";

    @Id
    @Column(name = "id", nullable = false, length = 32)
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "good_name", nullable = true, length = 50)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "good_stock", nullable = true)
    public Integer getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(Integer goodStock) {
        this.goodStock = goodStock;
    }

    @Basic
    @Column(name = "good_price", nullable = false)
    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    @Basic
    @Column(name = "create_date", nullable = false)
    @CreatedDate
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_by", nullable = false, length = 32)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "update_date", nullable = false)
    @LastModifiedDate
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "update_by", nullable = false, length = 32)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CGoodEntity that = (CGoodEntity) o;
        return goodPrice == that.goodPrice &&
                Objects.equals(id, that.id) &&
                Objects.equals(goodName, that.goodName) &&
                Objects.equals(goodStock, that.goodStock) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(updateBy, that.updateBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodName, goodStock, goodPrice, createDate, createBy, updateDate, updateBy);
    }
}
