package com.muyuan.goods.infrastructure.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.goods.domains.model.entity.AttributeValue;
import com.muyuan.goods.domains.repo.AttributeValueRepo;
import com.muyuan.goods.face.dto.AttributeValueQueryCommand;
import com.muyuan.goods.infrastructure.converter.AttributeValueConverter;
import com.muyuan.goods.infrastructure.dataobject.AttributeValueDO;
import com.muyuan.goods.infrastructure.mapper.AttributeValueMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.ID;
import static com.muyuan.goods.infrastructure.mapper.AttributeValueMapper.ATTRIBUTE_ID;
import static com.muyuan.goods.infrastructure.mapper.AttributeValueMapper.VALUE;

@Component
@AllArgsConstructor
public class AttributeValueRepoImpl implements AttributeValueRepo {

    private AttributeValueMapper attributeValueMapper;

    private AttributeValueConverter converter;

    @Override
    public Page<AttributeValue> select(AttributeValueQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(AttributeValueDO.class)
                .eq(ID,command.getId())
                .eq(ATTRIBUTE_ID,command.getAttributeId())
                .eq(VALUE,command.getValue());

        Page<AttributeValue> page = Page.<AttributeValue>builder().build();
        if (command.enablePage()) {
            page.setPageSize(command.getPageSize());
            page.setPageNum(command.getPageNum());
            sqlBuilder.page(page);
        }

        List<AttributeValueDO> list = attributeValueMapper.selectList(sqlBuilder.build());

        page.setRows(converter.to(list));

        return page;
    }


    @Override
    public boolean batchInsert(List<AttributeValue> attributeValues) {
        return  attributeValueMapper.batchInsert(converter.toDO(attributeValues)) == attributeValues.size();
    }

    @Override
    public List<AttributeValue> deleteBy(Long... ids) {
        List<AttributeValueDO> attributeValues = attributeValueMapper.selectList(new SqlBuilder(AttributeValueDO.class)
                .in(ID, ids)
                .build());

        attributeValueMapper.deleteBy(new SqlBuilder().in(ID, ids).build());

        return converter.to(attributeValues);
    }

}
