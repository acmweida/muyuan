package com.muyuan.goods.application.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.Goods;
import com.muyuan.goods.domains.repo.GoodsRepo;
import com.muyuan.goods.application.GoodsService;
import com.muyuan.goods.face.dto.GoodsQueryCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsServiceImpl
 * Description 商品服务
 * @Author 2456910384
 * @Date 2022/8/26 14:09
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    private GoodsRepo goodsRepo;

    @Override
    public Page<Goods> page(GoodsQueryCommand queryCommand) {
        return null;
    }
}
