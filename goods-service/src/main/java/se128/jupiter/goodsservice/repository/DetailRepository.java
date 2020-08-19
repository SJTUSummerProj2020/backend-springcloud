package se128.jupiter.goodsservice.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import se128.jupiter.goodsservice.entity.CDetail;

public interface DetailRepository extends MongoRepository<CDetail, ObjectId> {

    CDetail getDetailByGoodsId(Integer goodsId);

}
