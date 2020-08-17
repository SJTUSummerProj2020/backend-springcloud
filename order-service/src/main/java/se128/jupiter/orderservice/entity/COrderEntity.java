package se128.jupiter.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "c_order")
@EntityListeners(AuditingEntityListener.class)
public class COrderEntity {
    private String id;
    private String orderNo;
    private String orderStatus = "0";
    private Timestamp finishDate;
    private Timestamp deleteDate;
    private Timestamp createDate;
    private String createBy = "sys";
    private Timestamp updateDate;
    private String updateBy = "sys";
    private double amount;

    private List<COrderDetailEntity> cOrderDetailEntityList;

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
    @Column(name = "order_no", nullable = false, length = 50)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "order_status", nullable = false, length = 1)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "finish_date", nullable = false)
    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    @Basic
    @Column(name = "delete_date", nullable = true)
    public Timestamp getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Timestamp deleteDate) {
        this.deleteDate = deleteDate;
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

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @OneToMany(targetEntity = COrderDetailEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    public List<COrderDetailEntity> getcOrderDetailEntityList() {
        return cOrderDetailEntityList;
    }

    public void setcOrderDetailEntityList(List<COrderDetailEntity> cOrderDetailEntityList) {
        this.cOrderDetailEntityList = cOrderDetailEntityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        COrderEntity that = (COrderEntity) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(id, that.id) &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(orderStatus, that.orderStatus) &&
                Objects.equals(finishDate, that.finishDate) &&
                Objects.equals(deleteDate, that.deleteDate) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(updateBy, that.updateBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderNo, orderStatus, finishDate, deleteDate, createDate, createBy, updateDate, updateBy, amount);
    }

}
