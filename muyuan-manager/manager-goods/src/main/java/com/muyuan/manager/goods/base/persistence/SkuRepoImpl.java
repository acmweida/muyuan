package com.muyuan.manager.goods.base.persistence;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.manager.goods.base.persistence.mapper.SkuMapper;
import com.muyuan.manager.goods.dto.SkuDTO;
import com.muyuan.manager.goods.model.Sku;
import com.muyuan.manager.goods.repo.SkuRepo;
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
        return skuMapper.selectList(new LambdaQueryWrapper<Sku>()
                .eq(Sku::getId, skuDTO.getGoodsId()));
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
        skuMapper.updateById(sku);
    }
}
