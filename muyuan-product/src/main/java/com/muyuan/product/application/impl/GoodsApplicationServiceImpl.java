package com.muyuan.product.application.impl;

import com.muyuan.common.core.exception.handler.ResourceNotFoundException;
import com.muyuan.product.application.GoodsApplicationService;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.dto.SkuDTO;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.service.BrandService;
import com.muyuan.product.domains.service.GoodsCategoryService;
import com.muyuan.product.domains.service.GoodsService;
import com.muyuan.product.domains.service.SkuService;
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

    private BrandService brandService;

    private GoodsCategoryService goodsCategoryService;

    private GoodsService goodsService;

    private SkuService skuService;

    @Override
    @Transactional
    public void addGoods(GoodsDTO goodsDTO) {
        Goods goods = goodsDTO.convert();

        GoodsVO goodsVO = new GoodsVO();
        BeanUtils.copyProperties(goods, goodsVO);

        // 品牌信息
        Optional<Brand> brand = brandService.get(goods.getBrandId());
        goodsVO.setBrandName(brand.<ResourceNotFoundException>orElseThrow(() -> {
            throw new ResourceNotFoundException("品牌信息未找到");
        }).getName());

        // 类目信息
        Optional<GoodsCategory> goodsCategory = goodsCategoryService.get(GoodsCategory.builder()
                .code(goods.getCategoryCode())
                .build());

        goodsVO.setCategoryName(goodsCategory.<ResourceNotFoundException>orElseThrow(() -> {
            throw new ResourceNotFoundException("商品类型未找到");
        }).getName());

        List<SkuDTO> skus = goodsDTO.getSkus();

        int stock = 0;
        for (SkuDTO sku : skus) {
            stock += sku.getStock();
        }
        goodsVO.setStock(stock);

        skuService.addBatch(goodsDTO.getSkus());

        log.info("商品新增：{}",goodsVO);

        /**
         * 插入ES
         */


    }

}
