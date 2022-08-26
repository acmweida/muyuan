package com.muyuan.manager.product.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.manager.product.domains.dto.GoodsDTO;
import com.muyuan.manager.product.domains.model.Goods;

/**
 * @ClassName ProductService
 * Description 商品域服务接口
 * @Author 2456910384
 * @Date 2021/10/13 15:27
 * @Version 1.0
 */
public interface GoodsService {

    Page<Goods> page(GoodsDTO goodsDTO, Long shopId);

    /**
     * 添加商品
     * @return
     */
    void addGoods(GoodsDTO goodsDTO);

}
