package com.muyuan.manager.product.infrastructure.persistence;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.product.domains.dto.GoodsDTO;
import com.muyuan.manager.product.domains.model.Goods;
import com.muyuan.manager.product.domains.repo.GoodsRepo;
import com.muyuan.manager.product.infrastructure.persistence.mapper.GoodsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GoodsRepoImpl implements GoodsRepo {

    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> list(GoodsDTO goodsDTO) {
        return list(goodsDTO,null);
    }

    @Override
    public List<Goods> list(GoodsDTO goodsDTO, Page page) {
        return goodsMapper.selectList(new SqlBuilder(Goods.class)
                .eq(ID, goodsDTO.getShopId())
                .eq(CATEGORY_CODE, goodsDTO.getCategoryCode())
                .build());
    }

    @Override
    public void insert(Goods goods) {
        goodsMapper.insert(goods) ;
    }

    @Override
    public void update(Goods goods) {

    }
}
