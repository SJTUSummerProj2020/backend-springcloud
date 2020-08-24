package se128.jupiter.goodsservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se128.jupiter.goodsservice.entity.Auction;
import se128.jupiter.goodsservice.repository.AuctionRepository;

import java.util.List;


@Repository
public class AuctionDaoImpl{

    private final AuctionRepository auctionRepository;

    @Autowired
    public AuctionDaoImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<Auction> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        auctions.removeIf(item -> item.getDuration() < 0);
        return auctions;
    }

    public Auction getAuctionByAuctionId(Integer auctionId) {
        return auctionRepository.getAuctionByAuctionId(auctionId);
    }

    public Auction saveAuction(Auction auction) {
        return auctionRepository.saveAndFlush(auction);
    }

    public Auction addAuction(Auction auction){
        return auctionRepository.saveAndFlush(auction);
    }

    public void deleteAuctionByAuctionId(Integer auctionId) {
        Auction auction = auctionRepository.getAuctionByAuctionId(auctionId);
        auction.setDuration(-1);
        auctionRepository.saveAndFlush(auction);
    }

    public Auction editAuction(Auction auction) {
        return auctionRepository.saveAndFlush(auction);
    }
}
