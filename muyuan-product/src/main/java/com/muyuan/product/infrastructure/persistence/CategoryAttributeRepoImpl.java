package com.muyuan.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.repo.CategoryAttributeRepo;
import com.muyuan.product.infrastructure.persistence.mapper.CategoryAttributeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Component
@AllArgsConstructor
public class  CategoryAttributeRepoImpl implements CategoryAttributeRepo {

    private CategoryAttributeMapper categoryAttributeMapper;


    @Override
    public List<CategoryAttribute> select(CategoryAttributeDTO categoryAttributeDTO, Page page) {
        return  categoryAttributeMapper.selectList(new SqlBuilder(CategoryAttribute.class)
                .page(page)
                .build());
    }

    @Override
    public List<CategoryAttribute> select(CategoryAttributeDTO categoryAttributeDTO) {
        return select(categoryAttributeDTO,null);
    }

    @Override
    public void insert(CategoryAttribute categoryAttribute) {
        categoryAttributeMapper.insertAuto(categoryAttribute);
    }

    @Override
    public void update(CategoryAttribute categoryAttribute) {
        categoryAttributeMapper.updateBy(categoryAttribute,"id");
    }

    @Override
    public void delete(String... ids) {
         categoryAttributeMapper.deleteBy(new SqlBuilder(CategoryAttribute.class)
                .in("id",ids)
                .build());
    }

    @Override
    public CategoryAttribute selectOne(CategoryAttribute categoryAttribute) {
        return categoryAttributeMapper.selectOne(new SqlBuilder(CategoryAttribute.class)
                .eq("id",categoryAttribute.getId())
                .eq("name",categoryAttribute.getName())
                .eq("categoryCode",categoryAttribute.getCategoryCode())
                .build());
    }

}
