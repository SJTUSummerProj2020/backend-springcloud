package se128.jupiter.goodsservice.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@Document(collection = "goodsdetail")
public class Detail {

    @Id
    @Field(name = "id")
    private ObjectId id;

    @Field(name = "goods_id")
    private Integer goodsId;

    @Field(name = "detail")
    private String detail;

}
