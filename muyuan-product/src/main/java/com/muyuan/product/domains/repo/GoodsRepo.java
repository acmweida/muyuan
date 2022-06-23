package com.muyuan.product.domains.repo;

import com.muyuan.product.domains.model.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsRepo {

    List<Goods> queryList(Map param);

    boolean insert(Goods product);
}
