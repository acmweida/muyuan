package com.muyuan.product.infrastructure.persistence;

import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.infrastructure.persistence.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GoodsRepoImpl implements GoodsRepo {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> queryList(Map param) {
        return goodsMapper.selectList(param);
    }

    @Override
    public boolean insert(Goods product) {
        return goodsMapper.insert(product) > 0 ;
    }
}
