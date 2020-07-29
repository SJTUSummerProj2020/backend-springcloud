package se128.jupiter.goodsservice.repository.repositoryImpl;

import entity.Goods;
import org.springframework.data.domain.*;
import se128.jupiter.goodsservice.repository.GoodsRepository;

import java.util.List;
import java.util.Optional;

public class GoodsRepositoryImpl implements GoodsRepository {
    @Override
    public List<Goods> getAllGoods() {
        return null;
    }

    @Override
    public Goods getGoodsByGoodsId(Integer goodsId) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByGoodsType(Integer goodsType) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByName(String name) {
        return null;
    }

    @Override
    public List<Goods> findAllByNameLike(String name) {
        return null;
    }

    @Override
    public List<Goods> findAllByNameContains(String name) {
        return null;
    }

    @Override
    public List<Goods> getGoodsByPageId(Integer pageId) {
        return null;
    }

    @Override
    public Page<Goods> findByGoodsType(Integer goodsType, PageRequest pageable) {
        return null;
    }

    @Override
    public List<Goods> getPopularGoods(Integer number, Integer goodsType) {
        return null;
    }

    @Override
    public List<Goods> getPopularGoodsInAll(Integer number) {
        return null;
    }

    @Override
    public List<Goods> getRecommendGoodsByGoodsType(Integer goodsType, Integer number) {
        return null;
    }

    @Override
    public List<Goods> getRecommendGoodsInAll(Integer number) {
        return null;
    }

    @Override
    public List<Goods> findAll() {
        return null;
    }

    @Override
    public List<Goods> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Goods> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Goods> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Goods goods) {

    }

    @Override
    public void deleteAll(Iterable<? extends Goods> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Goods> S save(S s) {
        return null;
    }

    @Override
    public <S extends Goods> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Goods> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Goods> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Goods> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Goods getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Goods> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Goods> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Goods> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Goods> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Goods> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Goods> boolean exists(Example<S> example) {
        return false;
    }
}
