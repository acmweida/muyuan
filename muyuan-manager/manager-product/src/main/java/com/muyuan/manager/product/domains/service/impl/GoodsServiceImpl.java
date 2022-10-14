package com.muyuan.manager.product.domains.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.core.exception.ResourceNotFoundException;
import com.muyuan.manager.product.domains.dto.GoodsDTO;
import com.muyuan.manager.product.domains.dto.SkuDTO;
import com.muyuan.manager.product.domains.model.Brand;
import com.muyuan.manager.product.domains.model.Category;
import com.muyuan.manager.product.domains.model.Goods;
import com.muyuan.manager.product.domains.model.Sku;
import com.muyuan.manager.product.domains.repo.GoodsRepo;
import com.muyuan.manager.product.domains.repo.SkuRepo;
import com.muyuan.manager.product.domains.service.BrandService;
import com.muyuan.manager.product.domains.service.CategoryService;
import com.muyuan.manager.product.domains.service.GoodsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private SkuRepo skuRepo;

    private BrandService brandService;

    private CategoryService categoryService;

    @Override
    public Page<Goods> page(GoodsDTO goodsDTO, Long shopId) {
        Page page = Page.builder()
                .pageSize(goodsDTO.getPageSize())
                .pageNum(goodsDTO.getPageNum())
                .build();

        goodsDTO.setShopId(shopId);
        List<Goods> goodsList = goodsRepo.list(goodsDTO, page);
        page.setRows(goodsList);

        return page;
    }

    @Override
    @Transactional
    public void addGoods(GoodsDTO goodsDTO) {

        Goods goods = goodsDTO.convert();

        // 品牌信息
        Optional<Brand> brand = brandService.get(goods.getBrandId());
        goods.setBrandName(brand.<ResourceNotFoundException>orElseThrow(() -> {
            throw new ResourceNotFoundException("品牌信息未找到");
        }).getName());

        // 类目信息
        Optional<Category> goodsCategory = categoryService.get(Category.builder()
                .code(goods.getCategoryCode())
                .build());

        goods.setCategoryName(goodsCategory.<ResourceNotFoundException>orElseThrow(() -> {
            throw new ResourceNotFoundException("商品类型未找到");
        }).getName());

        List<Sku> skus = goodsDTO.getSkus().stream().map(SkuDTO::convert).collect(Collectors.toList());

        int stock = 0;
        for (Sku sku : skus) {
            stock += sku.getStock();
        }
        goods.setStock(stock);

        goods.initInstance();
        goods.setSkus(skus);
        goods.save(goodsRepo, skuRepo);

        log.info("商品新增：{}", goods);

    }


}
