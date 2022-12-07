package com.muyuan.manager.goods.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.common.BaseRepo;
import com.muyuan.manager.goods.dto.SkuDTO;
import com.muyuan.manager.goods.model.Sku;

import java.util.List;

public interface SkuRepo extends BaseRepo {

    List<Sku> list(SkuDTO skuDTO);

    List<Sku> list(SkuDTO skuDTO, Page page);

    void insert(Sku sku);

    void batchInsert(List<Sku> skus);

    void update(Sku sku);
}
