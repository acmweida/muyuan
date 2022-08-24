package com.muyuan.goods.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.goods.domains.model.entity.Goods;
import com.muyuan.goods.face.dto.GoodsDTO;
import org.apache.dubbo.common.utils.Page;

import java.util.List;

public interface GoodsRepo extends BaseRepo {

    List<Goods> list(GoodsDTO goodsDTO);

    List<Goods> list(GoodsDTO goodsDTO, Page page);

    void insert(Goods goods);

    void update(Goods goods);
}
