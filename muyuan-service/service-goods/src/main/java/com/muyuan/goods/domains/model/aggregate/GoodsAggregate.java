package com.muyuan.goods.domains.model.aggregate;

import com.muyuan.common.core.domains.mode.aggregate.Aggregate;
import com.muyuan.goods.domains.model.entity.Goods;
import com.muyuan.goods.domains.model.valueobject.GoodsId;
import com.muyuan.goods.domains.model.entity.Sku;

import java.util.List;

/**
 * @ClassName GoodsAggregate
 * Description 商品聚会
 * @Author 2456910384
 * @Date 2022/8/24 17:17
 * @Version 1.0
 */
public class GoodsAggregate implements Aggregate<GoodsId> {

    private GoodsId id;

    private Goods goods;

    private List<Sku> skus;

    @Override
    public GoodsId getId() {
        return id;
    }
}
