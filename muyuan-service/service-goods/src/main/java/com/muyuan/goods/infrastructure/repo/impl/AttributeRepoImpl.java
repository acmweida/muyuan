package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.JdbcBaseMapper;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.domains.repo.AttributeRepo;
import com.muyuan.goods.face.dto.AttributeQueryCommand;
import com.muyuan.goods.infrastructure.converter.AttributeConverter;
import com.muyuan.goods.infrastructure.dataobject.AttributeDO;
import com.muyuan.goods.infrastructure.mapper.AttributeMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;

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
    public Attribute selectAttribute(Long id) {
        AttributeDO attributeDO = attributeMapper.selectOne(new SqlBuilder(AttributeDO.class)
                .eq(ID, id)
                .build());
        return converter.to(attributeDO);
    }

    @Override
    public Attribute selectAttribute(Attribute.Identify identify) {
        AttributeDO attributeDO = attributeMapper.selectOne(new SqlBuilder(AttributeDO.class).select(ID)
                .eq(ID, identify.getId())
                .eq(CATEGORY_CODE,identify.getCategoryCode())
                .eq(NAME,identify.getName())
                .build());

        return converter.to(attributeDO);
    }

    @Override
    public boolean addAttribute(Attribute attribute) {
        AttributeDO to = converter.to(attribute);
        Integer count = attributeMapper.insertAuto(to);
        return count > 0;
    }

    @Override
    public Attribute updateAttribute(Attribute attribute) {
        SqlBuilder sqlBuilder = new SqlBuilder(AttributeDO.class)
                .eq(ID, attribute.getId());

        AttributeDO attributeDO = attributeMapper.selectOne(sqlBuilder.build());
        if (ObjectUtils.isNotEmpty(attributeDO)) {
            attributeMapper.updateBy(converter.to(attribute), ID);
        }

        return converter.to(attributeDO);
    }

    @Override
    public List<Attribute> deleteBy(Long... ids) {
        List<AttributeDO> attributes = attributeMapper.selectList(new SqlBuilder(AttributeDO.class)
                .in(ID, ids)
                .build());

        attributeMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(attributes);
    }

}
