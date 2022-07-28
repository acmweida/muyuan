package com.muyuan.product.application;

import com.muyuan.product.domains.dto.GoodsDTO;

/**
 * @ClassName GoodsApplicationService 接口
 * Description TODO
 * @Author 2456910384
 * @Date 2022/7/28 15:40
 * @Version 1.0
 */
public interface GoodsApplicationService {

    /**
     * 添加商品
     * @return
     */
    void addGoods(GoodsDTO goodsDTO);
}
