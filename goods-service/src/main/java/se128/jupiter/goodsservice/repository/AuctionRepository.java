package se128.jupiter.goodsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se128.jupiter.goodsservice.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction,Integer> {

    Auction getAuctionByAuctionId(Integer auctionId);
}
