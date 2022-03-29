package com.muyuan.system.application.query.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.system.application.query.DictDataQuery;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class DictDataQueryImpl implements DictDataQuery {

    DictDataRepo dictDataRepo;

    @Override
    public List<DictData> getByDataType(String dataType) {

        if (StringUtils.isBlank(dataType)) {
            return Collections.EMPTY_LIST;
        }

        List<DictData> list = dictDataRepo.select(new SqlBuilder(DictData.class)
                .eq("type", dataType)
                .eq("status", 0)
                .build());

        return list;
    }
}
