package com.muyuan.manager.product.infrastructure.persistence;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.product.infrastructure.persistence.mapper.AttributeMapper;
import com.muyuan.manager.product.domains.dto.AttributeDTO;
import com.muyuan.manager.product.domains.model.Attribute;
import com.muyuan.manager.product.domains.repo.AttributeRepo;
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
public class AttributeRepoImpl implements AttributeRepo {

    private AttributeMapper attributeMapper;


    @Override
    public List<Attribute> select(AttributeDTO attributeDTO, Page page) {
        return attributeMapper.selectList(new SqlBuilder(Attribute.class)
                .eq(CODE, attributeDTO.getCode())
                .eq(CATEGORY_CODE, attributeDTO.getCategoryCode())
                .page(page)
                .build());
    }

    @Override
    public List<Attribute> select(AttributeDTO attributeDTO) {
        return select(attributeDTO, null);
    }

    @Override
    public void insert(Attribute attribute) {
        attributeMapper.insertAuto(attribute);
    }

    @Override
    public void update(Attribute attribute) {
        attributeMapper.updateBy(attribute, "id");
    }

    @Override
    public void delete(String... ids) {
        attributeMapper.deleteBy(new SqlBuilder(Attribute.class)
                .in("id", ids)
                .build());
    }

    @Override
    public Attribute selectOne(Attribute attribute) {
        return attributeMapper.selectOne(new SqlBuilder(Attribute.class)
                .eq("id", attribute.getId())
                .eq("name", attribute.getName())
                .eq("categoryCode", attribute.getCategoryCode())
                .build());
    }

}
