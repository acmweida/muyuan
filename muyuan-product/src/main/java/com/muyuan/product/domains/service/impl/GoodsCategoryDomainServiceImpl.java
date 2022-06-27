package com.muyuan.product.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.GoodsCategoryRepo;
import com.muyuan.product.domains.service.GoodsCategoryDomainService;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductCategoryDomainServiceImpl
 * Description 商品分类
 * @Author 2456910384
 * @Date 2022/6/10 8:57
 * @Version 1.0
 */
@Service
@Slf4j
@AllArgsConstructor
public class GoodsCategoryDomainServiceImpl implements GoodsCategoryDomainService {

    private GoodsCategoryRepo goodsCategoryRepo;

    @Override
    public List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryRepo.list(goodsCategoryDTO);
    }

    @Override
    @Transactional
    public void add(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory goodsCategory = goodsCategoryDTO.convert();
        goodsCategory.init();
        if (ObjectUtils.isNotEmpty(goodsCategory.getParentId())) {
            GoodsCategory parent = goodsCategoryRepo.selectOne(
                    GoodsCategory.builder().id(goodsCategoryDTO.getParentId()).build());
            if (ObjectUtils.isEmpty(parent)) {
                addRootCategory(goodsCategory);
            } else {
                addSubNodeCategory(goodsCategory,parent);
            }
        } else {
            addRootCategory(goodsCategory);
        }
    }

    private void  addRootCategory(GoodsCategory goodsCategory) {
        int rootCount = goodsCategoryRepo.count(GoodsCategoryDTO.builder().level(1).build());
        goodsCategory.initRoot(rootCount);
        goodsCategory.save(goodsCategoryRepo);
        goodsCategory.setAncestors(String.valueOf(goodsCategory.getId()));
        goodsCategory.save(goodsCategoryRepo);
    }

    private void addSubNodeCategory(GoodsCategory goodsCategory, GoodsCategory parent) {
        goodsCategory.save(goodsCategoryRepo);
        // 查询兄弟节点数量 用于生成Code
        int count = goodsCategoryRepo.count(GoodsCategoryDTO.builder()
                .level(parent.getLevel() + 1)
                .parentId(parent.getId())
                .build());
        goodsCategory.linkParent(parent,count);
        goodsCategory.save(goodsCategoryRepo);
        parent.save(goodsCategoryRepo);
    }


    @Override
    public void update(GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory goodsCategory = goodsCategoryDTO.convert();
        goodsCategory.save(goodsCategoryRepo);
    }

    @Override
    public String checkUnique(GoodsCategory goodsCategory) {
        Long id = null == goodsCategory.getId() ? 0 : goodsCategory.getId();
        goodsCategory = goodsCategoryRepo.selectOne(
                GoodsCategory.builder().name(goodsCategory.getName())
                        .parentId(goodsCategory.getParentId()).build());
        if (null != goodsCategory && !id.equals(goodsCategory.getId())) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    @Override
    public Optional<GoodsCategory> get(GoodsCategory category) {
        GoodsCategory goodsCategory = goodsCategoryRepo.selectOne(category);
        return Optional.ofNullable(goodsCategory);
    }

    @Override
    public void delete(String[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        goodsCategoryRepo.delete(ids);
    }
}
