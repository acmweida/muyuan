package com.muyuan.manager.product.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.product.domains.model.Sku;
import com.muyuan.manager.product.domains.dto.SkuDTO;

import java.util.List;

public interface SkuRepo extends BaseRepo {

    List<Sku> list(SkuDTO skuDTO);

    List<Sku> list(SkuDTO skuDTO, Page page);

    void insert(Sku sku);

    void batchInsert(List<Sku> skus);

    void update(Sku sku);
}
