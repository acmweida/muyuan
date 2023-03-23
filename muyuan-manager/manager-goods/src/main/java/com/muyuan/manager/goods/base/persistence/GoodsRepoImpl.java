package com.muyuan.manager.goods.base.persistence;

import com.muyuan.common.bean.Page;

import com.muyuan.manager.goods.dto.GoodsDTO;
import com.muyuan.manager.goods.model.Goods;
import com.muyuan.manager.goods.repo.GoodsRepo;
import com.muyuan.manager.goods.base.persistence.mapper.GoodsMapper;
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
        return goodsMapper.selectList(new LambdaQueryWrapper(Goods.class)
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
