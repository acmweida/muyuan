package com.muyuan.goods.api.service;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.api.dto.GoodsDTO;
import com.muyuan.goods.api.dto.GoodsQueryRequest;

/**
 * @ClassName GoodsServiceApi 接口
 * Description 商品服务API
 * @Author 2456910384
 * @Date 2022/8/25 15:50
 * @Version 1.0
 */

public interface GoodsServiceApi {

    Page<GoodsDTO> page(GoodsQueryRequest request);
}
