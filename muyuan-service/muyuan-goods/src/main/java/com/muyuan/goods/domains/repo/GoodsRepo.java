package com.muyuan.goods.domains.repo;

import com.muyuan.common.core.domains.repo.Repository;
import com.muyuan.goods.domains.model.aggregate.GoodsAggregate;
import com.muyuan.goods.domains.model.entity.goods.GoodsId;

public interface GoodsRepo extends Repository<GoodsAggregate, GoodsId> {


}
