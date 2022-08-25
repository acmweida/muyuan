package com.muyuan.goods.api.service;

import com.muyuan.goods.api.dataobject.GoodsDO;
import com.muyuan.goods.api.to.GoodsTO;

import java.util.List;

/**
 * @ClassName GoodsServiceApi 接口
 * Description 商品服务API
 * @Author 2456910384
 * @Date 2022/8/25 15:50
 * @Version 1.0
 */

public interface GoodsServiceApi {

    List<GoodsDO> page(GoodsTO goodsTO);
}
