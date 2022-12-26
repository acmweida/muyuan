package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.JdbcBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.domains.model.entity.Brand;
import com.muyuan.goods.domains.repo.AttributeRepo;
import com.muyuan.goods.face.dto.AttributeQueryCommand;
import com.muyuan.goods.infrastructure.converter.AttributeConverter;
import com.muyuan.goods.infrastructure.dataobject.AttributeDO;
import com.muyuan.goods.infrastructure.dataobject.BrandDO;
import com.muyuan.goods.infrastructure.mapper.AttributeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;
import static com.muyuan.goods.infrastructure.mapper.BrandMapper.AUDIT_STATUS;

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

    private AttributeConverter converter;

    @Override
    public Page<Attribute> select(AttributeQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(AttributeDO.class)
                .eq(CODE, command.getCode())
                .eq(JdbcBaseMapper.CATEGORY_CODE, command.getCategoryCode());

        Page<Attribute> page = Page.<Attribute>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<AttributeDO> list = attributeMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));
        return page;

    }


    @Override
    public void delete(Long... ids) {
        attributeMapper.deleteBy(new SqlBuilder(Attribute.class)
                .in("id", ids)
                .build());
    }

}
