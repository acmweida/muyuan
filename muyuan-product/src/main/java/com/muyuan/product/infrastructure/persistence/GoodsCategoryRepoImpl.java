package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.GoodsCategoryRepo;
import com.muyuan.product.infrastructure.persistence.mapper.CategoryAttributeMapper;
import com.muyuan.product.infrastructure.persistence.mapper.GoodsCategoryMapper;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ProductCategoryRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/6/10 11:48
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class GoodsCategoryRepoImpl implements GoodsCategoryRepo {

    private GoodsCategoryMapper goodsCategoryMapper;

    private CategoryAttributeMapper categoryAttributeMapper;

    @Override
    public List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryMapper.selectList(new SqlBuilder(GoodsCategory.class)
                .like("name", goodsCategoryDTO.getName())
                .eq("code", goodsCategoryDTO.getCode())
                .eq("parentId", goodsCategoryDTO.getParentId())
                .eq("leaf",goodsCategoryDTO.getLeaf())
                .notEq("status","2")
                .build());
    }

    public List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO,String... column) {
        return goodsCategoryMapper.selectList(new SqlBuilder(GoodsCategory.class)
                .select(column)
                .eq("code", goodsCategoryDTO.getCode())
                .eq("parentId", goodsCategoryDTO.getParentId())
                .in("parentId", (Object) goodsCategoryDTO.getParentIds())
                .notEq("status","2")
                .build());
    }

    @Override
    public GoodsCategory selectOne(GoodsCategory goodsCategory) {
        return goodsCategoryMapper.selectOne(new SqlBuilder(GoodsCategory.class)
                .eq("id", goodsCategory.getId())
                .eq("name", goodsCategory.getName())
                .eq("parentId", goodsCategory.getParentId())
                .eq("code", goodsCategory.getCode())
                .eq("level", goodsCategory.getLevel())
                .notEq("status","2")
                .build());
    }

    @Override
    public GoodsCategory selectDetail(GoodsCategory goodsCategory) {
        GoodsCategory category = goodsCategoryMapper.selectOne(new SqlBuilder(GoodsCategory.class)
                .eq("id", goodsCategory.getId())
                .eq("code", goodsCategory.getCode())
                .notEq("status","2")
                .build());
        if (ObjectUtils.isEmpty(category)) {
            return null;
        }
        List<CategoryAttribute> attributes = categoryAttributeMapper.selectList(new SqlBuilder(CategoryAttribute.class)
                .eq("categoryCode", category.getCode())
                .orderByAsc("type")
                .build());
        category.setAttributes(attributes);
        return category;
    }

    @Override
    public void insert(GoodsCategory product) {
        goodsCategoryMapper.insertAuto(product);
    }

    @Override
    public void update(GoodsCategory goodsCategory) {
        goodsCategoryMapper.updateBy(goodsCategory,"id");
    }

    @Override
    public void update(GoodsCategory goodsCategory, String... column) {
        goodsCategoryMapper.updateColumnBy(goodsCategory,column,"id");
    }

    @Override
    public int count(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryMapper.count(new SqlBuilder(GoodsCategory.class)
                .eq("level", goodsCategoryDTO.getLevel())
                .eq("parentId", goodsCategoryDTO.getParentId())
                .notEq("status","2")
                .build());
    }

    @Override
    public int countAll(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryMapper.count(new SqlBuilder(GoodsCategory.class)
                .eq("level", goodsCategoryDTO.getLevel())
                .eq("parentId", goodsCategoryDTO.getParentId())
                .build());
    }

    @Override
    public void delete(Long[] ids) {
        goodsCategoryMapper.update(new SqlBuilder()
                .set("status","2")
                .set("updater", SecurityUtils.getUsername())
                .set("updateBy", SecurityUtils.getUserId())
                .in("id",ids)
                .build());
    }


}
