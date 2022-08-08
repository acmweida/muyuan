package com.muyuan.product.domains.service;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.SkuDTO;
import com.muyuan.product.domains.model.Sku;

import java.util.List;

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
