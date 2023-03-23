package com.muyuan.goods.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
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

@Component
@AllArgsConstructor
public class AttributeRepoImpl implements AttributeRepo {

    private AttributeMapper attributeMapper;

    private AttributeConverter converter;

    @Override
    public Page<Attribute> select(AttributeQueryCommand command) {
        LambdaQueryWrapper<AttributeDO> wrapper = new LambdaQueryWrapper<AttributeDO>()
                .eq(AttributeDO::getCode, command.getCode())
                .eq(AttributeDO::getCategoryCode, command.getCategoryCode());

        Page<Attribute> page = Page.<Attribute>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<AttributeDO> list = command.enablePage() ?
                attributeMapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                attributeMapper.selectList(wrapper);

        page.setRows(converter.to(list));

        return page;
    }

    @Override
    public Attribute selectAttribute(Long id) {
        AttributeDO attributeDO = attributeMapper.selectOne(new LambdaQueryWrapper<AttributeDO>()
                .eq(AttributeDO::getId, id));
        return converter.to(attributeDO);
    }

    @Override
    public Attribute selectAttribute(Attribute.Identify identify) {
        AttributeDO attributeDO = attributeMapper.selectOne(new LambdaQueryWrapper<AttributeDO>().select(AttributeDO::getId)
                .eq(ObjectUtils.isNotEmpty(identify.getId()), AttributeDO::getId, identify.getId())
                .eq(AttributeDO::getCategoryCode, identify.getCategoryCode())
                .eq(AttributeDO::getName, identify.getName()));

        return converter.to(attributeDO);
    }

    @Override
    public boolean addAttribute(Attribute attribute) {
        AttributeDO to = converter.to(attribute);
        Integer count = attributeMapper.insert(to);
        attribute.setId(to.getId());
        return count > 0;
    }

    @Override
    public Attribute updateAttribute(Attribute attribute) {
        LambdaQueryWrapper<AttributeDO> wrapper = new LambdaQueryWrapper<AttributeDO>()
                .eq(AttributeDO::getId, attribute.getId());

        AttributeDO attributeDO = attributeMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(attributeDO)) {
            attributeMapper.updateById(converter.to(attribute));
        }

        return converter.to(attributeDO);
    }

    @Override
    public List<Attribute> deleteBy(Long... ids) {
        LambdaQueryWrapper<AttributeDO> wrapper = new LambdaQueryWrapper<AttributeDO>()
                .in(AttributeDO::getId, ids);
        List<AttributeDO> attributes = attributeMapper.selectList(wrapper);

        attributeMapper.delete(wrapper);

        return converter.to(attributes);
    }

}
