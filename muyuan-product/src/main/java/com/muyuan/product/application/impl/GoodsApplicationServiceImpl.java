package com.muyuan.product.application.impl;

import com.muyuan.common.core.exception.handler.ResourceNotFoundException;
import com.muyuan.product.application.GoodsApplicationService;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.model.Sku;
import com.muyuan.product.domains.service.BrandDomainService;
import com.muyuan.product.domains.service.GoodsCategoryDomainService;
import com.muyuan.product.domains.service.GoodsDomainService;
import com.muyuan.product.domains.vo.GoodsVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName GoodsApplicationServiceImpl
 * Description Impl
 * @Author 2456910384
 * @Date 2022/7/28 15:40
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class GoodsApplicationServiceImpl implements GoodsApplicationService {

    private BrandDomainService brandDomainService;

    private GoodsCategoryDomainService goodsCategoryDomainService;

    private GoodsDomainService goodsDomainService;

    @Override
    @Transactional
    public void addGoods(GoodsDTO goodsDTO) {
        Goods goods = goodsDTO.convert();

        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods,goodsVO);

        // 品牌信息
        Optional<Brand> brand = brandDomainService.get(goods.getBrandId());
        goodsVO.setBrandName(brand.orElseThrow(() -> {
            throw new ResourceNotFoundException("品牌信息未找到");
        }).getName());

        // 类目信息
        Optional<GoodsCategory> goodsCategory = goodsCategoryDomainService.get(GoodsCategory.builder()
                .code(goods.getCategoryCode())
                .build());

        goodsVO.setCategoryName(goodsCategory.orElseThrow(() -> {
            throw new ResourceNotFoundException("商品类型未找到");
        }).getName());

        List<Sku> skus = goodsDTO.getSkus();

        int stock = 0;
        for (Sku sku : skus) {
            stock += sku.getStock();
        }
        goodsVO.setStock(stock);

        /**
         * 插入ES
         */

    }

}
