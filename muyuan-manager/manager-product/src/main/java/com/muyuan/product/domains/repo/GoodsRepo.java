package com.muyuan.product.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;

import java.util.List;

public interface GoodsRepo extends BaseRepo {

    List<Goods> list(GoodsDTO goodsDTO);

    List<Goods> list(GoodsDTO goodsDTO, Page page);

    void insert(Goods goods);

    void update(Goods goods);
}
