package com.muyuan.product.domains.service.impl;

import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.SkuDTO;
import com.muyuan.product.domains.model.Sku;
import com.muyuan.product.domains.repo.SkuRepo;
import com.muyuan.product.domains.service.SkuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public void add(SkuDTO skuDTO) {
        Sku sku = skuDTO.convert();
        sku.initInstance();
        sku.save(skuRepo);
    }

    @Override
    public void addBatch(List<SkuDTO> skuDTOS) {
        List<Sku> skus = new ArrayList<>(skuDTOS.size());
        Date now = DateTime.now().toDate();
        for (SkuDTO skuDTO : skuDTOS) {
            Sku sku = skuDTO.convert();
            sku.initInstance(now);;
            skus.add(sku);
        }
        skuRepo.batchInsert(skus);
    }
}
