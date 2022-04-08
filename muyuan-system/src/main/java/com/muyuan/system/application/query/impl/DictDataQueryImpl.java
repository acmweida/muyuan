package com.muyuan.system.application.query.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.application.query.DictDataQuery;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class DictDataQueryImpl implements DictDataQuery {

    private DictDataRepo dictDataRepo;

    @Override
    public Page list(DictDataDTO dictDataDTO) {

        SqlBuilder sqlBuilder = new SqlBuilder(DictData.class);
        if (ObjectUtils.isNotEmpty(dictDataDTO.getType())) {
            sqlBuilder.eq("type",dictDataDTO.getType());
        }
        if (ObjectUtils.isNotEmpty(dictDataDTO.getStatus())) {
            sqlBuilder.eq("status",dictDataDTO.getStatus());
        }

        Page page = new Page();
        page.setPageNum(dictDataDTO.getPageNum());
        page.setPageSize(dictDataDTO.getPageSize());
        sqlBuilder.page(page);

        List<DictData> list = dictDataRepo.select(sqlBuilder.build());

        page.setRows(list);

        return page;
    }

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
