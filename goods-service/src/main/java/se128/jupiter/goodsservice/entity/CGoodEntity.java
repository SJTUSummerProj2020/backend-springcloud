package se128.jupiter.goodsservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "goods")
@EntityListeners(AuditingEntityListener.class)
public class CGoodEntity {

    private Integer goodsId;
    private String name;
    private String startTime;
    private String endTime;
    private String address;
    private String website;
    private Integer goodsType;
    private String image;
    private Integer viewCounter = 0;
    private Integer buyCounter = 0;
    private List<CGoodsDetail> goodsDetails;
    private String detail;

    @Id
    @Column(name = "goods_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId)
    {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "name", nullable = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "address", nullable = true)
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "website", nullable = true)
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    @Basic
    @Column(name = "goods_type", nullable = true)
    public Integer getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    @Basic
    @Column(name = "image", nullable = true)
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "view_counter", nullable = true)
    public Integer getViewCounter() {
        return viewCounter;
    }
    public void setViewCounter(Integer viewCounter) {
        this.viewCounter = viewCounter;
    }

    @Basic
    @Column(name = "buy_counter", nullable = true)
    public Integer getBuyCounter() {
        return buyCounter;
    }
    public void setBuyCounter(Integer buyCounter) {
        this.buyCounter = buyCounter;
    }

    @OneToMany(targetEntity = CGoodsDetail.class,cascade =CascadeType.ALL)
    @JoinColumn(name = "goods_id")
    public List<CGoodsDetail> getGoodsDetails()
    {
        return goodsDetails;
    }
    public void setGoodsDetails(List<CGoodsDetail> goodsDetails)
    {
        this.goodsDetails= goodsDetails;
    }

    @Transient
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
