package com.muyuan.manager.goods.domains.service;

import com.muyuan.common.bean.Page;
import com.muyuan.manager.goods.domains.dto.GoodsDTO;
import com.muyuan.manager.goods.domains.model.Goods;

/**
 * @ClassName GoodsService
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
