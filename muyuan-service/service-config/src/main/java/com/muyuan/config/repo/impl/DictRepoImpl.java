package com.muyuan.config.repo.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.repo.DictRepo;
import com.muyuan.config.face.dto.DictTypeQueryCommand;
import com.muyuan.config.repo.converter.DictConverter;
import com.muyuan.config.repo.dataobject.DictDataDO;
import com.muyuan.config.repo.dataobject.DictTypeDO;
import com.muyuan.config.repo.mapper.DictDataMapper;
import com.muyuan.config.repo.mapper.DictTypeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.muyuan.common.mybatis.jdbc.JdbcBaseMapper.*;

/**
 * @ClassName DictDataRepoImpl
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 11:11
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class DictRepoImpl implements DictRepo {

    private DictDataMapper dictDataMapper;

    private DictTypeMapper dictTypeMapper;

    private DictConverter converter;

    @Override
    public List<DictData> selectByDictType(String dataType) {
        final List<DictDataDO> dictDataDOS = dictDataMapper.selectList(new SqlBuilder(DictData.class)
                .eq(TYPE, dataType)
                .eq(STATUS, 0)
                .orderByAsc(ORDER_NUM)
                .build());

        return converter.to(dictDataDOS);
    }

    @Override
    public Page<DictType> select(DictTypeQueryCommand command) {
        SqlBuilder sqlBuilder = new SqlBuilder(DictType.class)
                .eq(NAME, command.getName())
                .eq(TYPE, command.getType())
                .eq(STATUS, command.getStatus())
                .orderByDesc(UPDATE_TIME, CREATE_TIME);

        Page<DictType> page = Page.<DictType>builder().pageSize(command.getPageSize())
                .pageNum(command.getPageNum()).build();
        sqlBuilder.page(page);

        List<DictTypeDO> list = dictTypeMapper.selectList(sqlBuilder.build());

        page.setRows(converter.toTypeDTO(list));

        return page;
    }

    @Override
    public DictType selectDictType(DictType type) {
        DictTypeDO dictType = dictTypeMapper.selectOne(new SqlBuilder(DictType.class).select("id")
                .eq(NAME, type.getName())
                .eq(TYPE, type.getType())
                .eq(ID,type.getId())
                .build());

        return converter.to(dictType);
    }

    @Override
    public boolean addDictType(DictType type) {
        Integer count = dictTypeMapper.insertAuto(converter.to(type));
        return  count > 0;
    }
}
