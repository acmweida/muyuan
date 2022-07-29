package com.muyuan.product.domains.service.impl;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.repo.GoodsRepo;
import com.muyuan.product.domains.service.GoodsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class GoodsServiceImpl implements GoodsService {

    private GoodsRepo goodsRepo;

    @Override
    public Page<Goods> page(GoodsDTO goodsDTO,Long shopId) {
        Page page = Page.builder()
                .pageSize(goodsDTO.getPageSize())
                .pageNum(goodsDTO.getPageNum())
                .build();

        goodsDTO.setShopId(shopId);
        List<Goods> goodsList = goodsRepo.list(goodsDTO,page);
        page.setRows(goodsList);

        return page;
    }

    @Override
    public void add(GoodsDTO goodsDTO) {
        Goods goods = goodsDTO.convert();
        goods.initInstance();
        goods.save(goodsRepo);

    }
}
