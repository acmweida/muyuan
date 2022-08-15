package com.muyuan.manager.product.domains.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.product.domains.dto.SkuDTO;
import com.muyuan.manager.product.domains.model.Sku;

/**
 * @ClassName ProductService
 * Description Sku域服务接口
 * @Author 2456910384
 * @Date 2021/10/13 15:27
 * @Version 1.0
 */
public interface SkuService {

    Page<Sku> page(SkuDTO goodsDTO, Long goodsId);

}
