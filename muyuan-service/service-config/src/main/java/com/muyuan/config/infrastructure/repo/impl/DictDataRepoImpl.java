package com.muyuan.config.infrastructure.repo.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.config.domains.model.entity.DictData;
import com.muyuan.config.domains.repo.DictDataRepo;
import com.muyuan.config.infrastructure.repo.converter.DictConverter;
import com.muyuan.config.infrastructure.repo.dataobject.DictDataDO;
import com.muyuan.config.infrastructure.repo.mapper.DictDataMapper;
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
public class DictDataRepoImpl implements DictDataRepo {

    private DictDataMapper dictDataMapper;

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
}
