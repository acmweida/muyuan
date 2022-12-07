package com.muyuan.manager.goods.service;

import com.muyuan.common.bean.Page;
import com.muyuan.manager.goods.dto.SkuDTO;
import com.muyuan.manager.goods.model.Sku;

/**
 * @ClassName GoodsService
 * Description Sku域服务接口
 * @Author 2456910384
 * @Date 2021/10/13 15:27
 * @Version 1.0
 */
public interface SkuService {

    Page<Sku> page(SkuDTO goodsDTO, Long goodsId);

}
