package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.repo.GoodsRepo;
import com.muyuan.goods.face.dto.GoodsDTO;
import com.muyuan.goods.infrastructure.mapper.GoodsMapper;
import com.muyuan.goods.infrastructure.po.GoodsPO;
import lombok.AllArgsConstructor;
import org.apache.dubbo.common.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GoodsRepoImpl implements GoodsRepo {

    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsPO> list(GoodsDTO goodsDTO) {
        return list(goodsDTO,null);
    }

    @Override
    public List<GoodsPO> list(GoodsDTO goodsDTO, Page page) {
       return  goodsMapper.selectList(new SqlBuilder(GoodsPO.class)
                .eq(ID, goodsDTO.getShopId())
                .eq(CATEGORY_CODE, goodsDTO.getCategoryCode())
                .build());

    }

    @Override
    public void insert(GoodsPO goods) {
        goodsMapper.insert(goods) ;
    }

    @Override
    public void update(GoodsPO goods) {

    }
}
