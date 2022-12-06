package com.muyuan.manager.goods.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.manager.goods.domains.dto.SkuDTO;
import com.muyuan.manager.goods.domains.model.Sku;
import com.muyuan.manager.goods.domains.repo.SkuRepo;
import com.muyuan.manager.goods.domains.service.SkuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodsServiceImpl
 * Description 商品域服务实现
 * @Author 2456910384
 * @Date 2021/10/13 15:28
 * @Version 1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class SkuServiceImpl implements SkuService {

    private SkuRepo skuRepo;

    @Override
    public Page<Sku> page(SkuDTO skuDTO, Long goodsId) {
        Page page = Page.builder()
                .pageSize(skuDTO.getPageSize())
                .pageNum(skuDTO.getPageNum())
                .build();

        skuDTO.setGoodsId(goodsId);
        List<Sku> goodsList = skuRepo.list(skuDTO,page);
        page.setRows(goodsList);

        return page;
    }


}
