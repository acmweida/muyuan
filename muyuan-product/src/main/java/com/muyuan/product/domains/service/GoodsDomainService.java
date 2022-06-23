package com.muyuan.product.domains.service;

import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductService
 * Description 商品域服务接口
 * @Author 2456910384
 * @Date 2021/10/13 15:27
 * @Version 1.0
 */
public interface GoodsDomainService {

    /**
     * 添加商品
     * @return
     */
    Optional<Long> addGoods();

    List<Goods> queryGoodsByShopInfo(GoodsDTO shopGoodsDTO);
}
