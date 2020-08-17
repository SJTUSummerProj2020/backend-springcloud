package se128.jupiter.orderservice.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "c_order_detail")
@EntityListeners(AuditingEntityListener.class)
public class COrderDetailEntity {
    private String id;
    private String orderId;
    private String goodId;
    private String goodName;
    private double price;
    private int quantity;
    private Double amount;
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
    @Column(name = "order_id", length = 32)
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "good_id", nullable = false, length = 32)
    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "good_name", nullable = false, length = 50)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
    @Column(name = "update_by", nullable = true, length = 32)
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
        COrderDetailEntity that = (COrderDetailEntity) o;
        return Double.compare(that.price, price) == 0 &&
                quantity == that.quantity &&
                Objects.equals(id, that.id) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(goodId, that.goodId) &&
                Objects.equals(goodName, that.goodName) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(updateBy, that.updateBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, goodId, goodName, price, quantity, amount, createDate, createBy, updateDate, updateBy);
    }

}
