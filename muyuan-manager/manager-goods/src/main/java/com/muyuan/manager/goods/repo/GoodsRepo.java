package com.muyuan.manager.goods.repo;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.common.BaseRepo;
import com.muyuan.manager.goods.dto.GoodsDTO;
import com.muyuan.manager.goods.model.Goods;

import java.util.List;

public interface GoodsRepo extends BaseRepo {

    List<Goods> list(GoodsDTO goodsDTO);

    List<Goods> list(GoodsDTO goodsDTO, Page page);

    void insert(Goods goods);

    void update(Goods goods);
}
