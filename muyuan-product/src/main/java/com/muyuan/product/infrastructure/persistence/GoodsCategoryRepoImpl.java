package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;
import com.muyuan.product.domains.model.BrandCategory;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.repo.GoodsCategoryRepo;
import com.muyuan.product.infrastructure.persistence.mapper.BrandCategoryMapper;
import com.muyuan.product.infrastructure.persistence.mapper.GoodsCategoryMapper;
import lombok.AllArgsConstructor;
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

    private BrandCategoryMapper brandCategoryMapper;

    @Override
    public List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryMapper.selectList(new SqlBuilder(GoodsCategory.class)
                .like(NAME, goodsCategoryDTO.getName())
                .eq(CODE, goodsCategoryDTO.getCode())
                .eq(PARENT_ID, goodsCategoryDTO.getParentId())
                .in(PARENT_ID, goodsCategoryDTO.getParentIds())
                .in(ID,  goodsCategoryDTO.getIds())
                .eq(LEAF, goodsCategoryDTO.getLeaf())
                .in(STATUS, STATUS_OK)
                .build());
    }

    public List<GoodsCategory> list(GoodsCategoryDTO goodsCategoryDTO, String... column) {
        return goodsCategoryMapper.selectList(new SqlBuilder(GoodsCategory.class)
                .select(column)
                .eq(CODE, goodsCategoryDTO.getCode())
                .eq(PARENT_ID, goodsCategoryDTO.getParentId())
                .in(PARENT_ID, (Object) goodsCategoryDTO.getParentIds())
                .in(STATUS, STATUS_OK)
                .build());
    }

    @Override
    public GoodsCategory selectOne(GoodsCategory goodsCategory) {
        return goodsCategoryMapper.selectOne(new SqlBuilder(GoodsCategory.class)
                .eq(ID, goodsCategory.getId())
                .eq(NAME, goodsCategory.getName())
                .eq(PARENT_ID, goodsCategory.getParentId())
                .eq(CODE, goodsCategory.getCode())
                .eq(LEVEL, goodsCategory.getLevel())
                .in(STATUS, STATUS_OK)
                .build());
    }

    @Override
    public void insert(GoodsCategory product) {
        goodsCategoryMapper.insertAuto(product);
    }

    @Override
    public void update(GoodsCategory goodsCategory) {
        goodsCategoryMapper.updateBy(goodsCategory, ID);

    }

    @Override
    public void update(GoodsCategory goodsCategory, String... column) {
        goodsCategoryMapper.updateColumnBy(goodsCategory, column, ID);
    }

    @Override
    public int count(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryMapper.count(new SqlBuilder(GoodsCategory.class)
                .eq(LEVEL, goodsCategoryDTO.getLevel())
                .eq(PARENT_ID, goodsCategoryDTO.getParentId())
                .in(STATUS, STATUS_OK)
                .build());
    }

    @Override
    public int countAll(GoodsCategoryDTO goodsCategoryDTO) {
        return goodsCategoryMapper.count(new SqlBuilder(GoodsCategory.class)
                .eq(LEVEL, goodsCategoryDTO.getLevel())
                .eq(PARENT_ID, goodsCategoryDTO.getParentId())
                .build());
    }

    @Override
    public void delete(Long[] ids) {
        goodsCategoryMapper.update(new SqlBuilder()
                .set(STATUS, STATUS_DELETE)
                .set(UPDATER, SecurityUtils.getUsername())
                .set(UPDATE_BY, SecurityUtils.getUserId())
                .in(ID, ids)
                .build());
    }

    @Override
    public List<BrandCategory> selectBrand(Long... categoryCode) {
        return brandCategoryMapper.selectList(new SqlBuilder(BrandCategory.class)
                .in(BrandCategoryMapper.CATEGORY_CODE, categoryCode)
                .build());
    }

}
