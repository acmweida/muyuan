package com.muyuan.manager.product.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.product.domains.model.Goods;
import com.muyuan.manager.product.domains.dto.GoodsDTO;

import java.util.List;

public interface GoodsRepo extends BaseRepo {

    List<Goods> list(GoodsDTO goodsDTO);

    List<Goods> list(GoodsDTO goodsDTO, Page page);

    void insert(Goods goods);

    void update(Goods goods);
}
