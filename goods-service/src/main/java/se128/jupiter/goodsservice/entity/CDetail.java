package se128.jupiter.goodsservice.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "goodsdetail")
public class CDetail {
    private ObjectId id;
    private Integer goodsId;
    private String detail;

    @Id
    @Field("id")
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    @Field("goods_id")
    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Field("detail")
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
}
