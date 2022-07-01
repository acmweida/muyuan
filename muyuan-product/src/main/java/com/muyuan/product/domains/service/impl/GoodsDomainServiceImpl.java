package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.JdbcValueConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.model.Sku;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.service.GoodsDomainService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductServiceImpl
 * Description 商品域服务实现
 * @Author 2456910384
 * @Date 2021/10/13 15:28
 * @Version 1.0
 */
@Slf4j
public class GoodsDomainServiceImpl implements GoodsDomainService {

    private Goods product;

    private List<Sku> skus;

    private Brand brand;

    private GoodsRepo goodsRepo;

    public GoodsDomainServiceImpl(Goods goods, GoodsRepo repo) {
        this.product = product;
    }

    public GoodsDomainServiceImpl(Goods goods, List<Sku> skus, Brand brand, GoodsRepo goodsRepo) {
        this.product = product;
        this.skus = skus;
        this.brand = brand;
        this.goodsRepo = goodsRepo;
    }

    @Override
    public Optional<Long> addGoods() {


        return Optional.empty();
    }

    @Override
    public List<Goods> queryGoodsByShopInfo(GoodsDTO shopGoodsDTO) {
        SqlBuilder sqlBuilder = new SqlBuilder(Goods.class)
                .eq("delete", JdbcValueConst.DELETE_FALSE)
                .eq("id", shopGoodsDTO.getShopId());
        sqlBuilder.eq("categoryCode", shopGoodsDTO.getCategoryCode());

        List<Goods> goodsList = goodsRepo.queryList(sqlBuilder.build());

        return goodsList;
    }
}
