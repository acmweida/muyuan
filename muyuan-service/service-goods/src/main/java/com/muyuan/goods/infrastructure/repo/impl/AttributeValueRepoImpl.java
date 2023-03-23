package com.muyuan.goods.infrastructure.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.muyuan.common.bean.Page;
import com.muyuan.goods.domains.model.entity.AttributeValue;
import com.muyuan.goods.domains.repo.AttributeValueRepo;
import com.muyuan.goods.face.dto.AttributeValueQueryCommand;
import com.muyuan.goods.infrastructure.converter.AttributeValueConverter;
import com.muyuan.goods.infrastructure.dataobject.AttributeValueDO;
import com.muyuan.goods.infrastructure.mapper.AttributeValueMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AttributeValueRepoImpl implements AttributeValueRepo {

    private AttributeValueMapper mapper;

    private AttributeValueConverter converter;

    @Override
    public Page<AttributeValue> select(AttributeValueQueryCommand command) {
        LambdaQueryWrapper<AttributeValueDO> wrapper = new LambdaQueryWrapper<AttributeValueDO>()
                .eq(AttributeValueDO::getAttributeId,command.getId())
                .eq(AttributeValueDO::getAttributeId,command.getAttributeId())
                .eq(AttributeValueDO::getValue,command.getValue());


        Page<AttributeValue> page = Page.<AttributeValue>builder()
                .pageNum(command.getPageNum())
                .pageSize(command.getPageSize())
                .build();

        List<AttributeValueDO> list = command.enablePage() ?
                mapper.page(wrapper, command.getPageSize(), command.getPageNum()).getRows() :
                mapper.selectList(wrapper);

        page.setRows(converter.to(list));
        return page;
    }


    @Override
    public boolean batchInsert(List<AttributeValue> attributeValues) {
        return  mapper.batchInsert(converter.toDO(attributeValues)) == attributeValues.size();
    }

    @Override
    public List<AttributeValue> deleteBy(Long... ids) {
        LambdaQueryWrapper<AttributeValueDO> wrapper = new LambdaQueryWrapper<AttributeValueDO>()
                .in(AttributeValueDO::getId, ids);
        List<AttributeValueDO> attributeValues = mapper.selectList(wrapper);

        mapper.delete(wrapper);

        return converter.to(attributeValues);
    }

}
