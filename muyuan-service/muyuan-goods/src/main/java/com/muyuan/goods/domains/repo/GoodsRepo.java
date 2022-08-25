package com.muyuan.goods.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.goods.face.dto.GoodsDTO;
import com.muyuan.goods.infrastructure.po.GoodsPO;
import org.apache.dubbo.common.utils.Page;

import java.util.List;

public interface GoodsRepo extends BaseRepo {

    List<GoodsPO> list(GoodsDTO goodsDTO);

    List<GoodsPO> list(GoodsDTO goodsDTO, Page page);

    void insert(GoodsPO goods);

    void update(GoodsPO goods);
}
