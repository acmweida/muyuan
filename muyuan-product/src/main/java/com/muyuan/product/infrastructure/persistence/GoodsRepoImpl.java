package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.infrastructure.persistence.mapper.GoodsMapper;
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
                .eq(DELETE, DELETE_FALSE)
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
