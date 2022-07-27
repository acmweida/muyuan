package com.muyuan.product.domains.service.impl;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.service.GoodsDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductServiceImpl
 * Description 商品域服务实现
 * @Author 2456910384
 * @Date 2021/10/13 15:28
 * @Version 1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class GoodsDomainServiceImpl implements GoodsDomainService {

    private GoodsRepo goodsRepo;

    @Override
    public Optional<Long> addGoods() {
        return Optional.empty();
    }

    @Override
    public Page<Goods> page(GoodsDTO shopGoodsDTO,Long shopId) {
        Page page = Page.builder()
                .pageSize(shopGoodsDTO.getPageSize())
                .pageNum(shopGoodsDTO.getPageNum())
                .build();

        shopGoodsDTO.setShopId(shopId);
        List<Goods> goodsList = goodsRepo.list(shopGoodsDTO,page);
        page.setRows(goodsList);

        return page;
    }
}
