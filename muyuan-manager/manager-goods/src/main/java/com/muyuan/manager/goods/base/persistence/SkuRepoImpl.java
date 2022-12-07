package com.muyuan.manager.goods.base.persistence;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.manager.goods.dto.SkuDTO;
import com.muyuan.manager.goods.model.Sku;
import com.muyuan.manager.goods.repo.SkuRepo;
import com.muyuan.manager.goods.base.persistence.mapper.SkuMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SkuRepoImpl implements SkuRepo {

    private SkuMapper skuMapper;

    @Override
    public List<Sku> list(SkuDTO skuDTO) {
        return list(skuDTO,null);
    }

    @Override
    public List<Sku> list(SkuDTO skuDTO, Page page) {
        return skuMapper.selectList(new SqlBuilder(Sku.class)
                .eq(ID, skuDTO.getGoodsId())
                .build());
    }

    @Override
    public void insert(Sku sku) {
        skuMapper.insert(sku) ;
    }

    @Override
    public void batchInsert(List<Sku> skus) {
            skuMapper.batchInsert(skus);
    }

    @Override
    public void update(Sku sku) {
        skuMapper.updateBy(sku,ID);
    }
}
