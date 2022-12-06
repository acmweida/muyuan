package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.goods.domains.model.aggregate.GoodsAggregate;
import com.muyuan.goods.domains.model.valueobject.GoodsId;
import com.muyuan.goods.domains.repo.GoodsRepo;
import com.muyuan.goods.infrastructure.mapper.GoodsMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsRepoImpl implements GoodsRepo {

    private GoodsMapper goodsMapper;

    @Override
    public GoodsAggregate find(GoodsId goodsId) {
        return null;
    }

    @Override
    public void remove(GoodsAggregate aggregate) {

    }

    @Override
    public void save(GoodsAggregate aggregate) {

    }

}
