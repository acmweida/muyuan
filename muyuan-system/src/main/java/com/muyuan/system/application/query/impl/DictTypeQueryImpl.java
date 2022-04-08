package com.muyuan.system.application.query.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.application.query.DictTypeQuery;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DictTypeQueryImpl implements DictTypeQuery {

    private DictTypeRepo dictTypeRepo;

    @Override
    public Page list(DictTypeDTO dictTypeDTO) {

        SqlBuilder sqlBuilder = new SqlBuilder(DictType.class);
        if (ObjectUtils.isNotEmpty(dictTypeDTO.getName())) {
            sqlBuilder.eq("name",dictTypeDTO.getName());
        }
        if (ObjectUtils.isNotEmpty(dictTypeDTO.getType())) {
            sqlBuilder.eq("type",dictTypeDTO.getType());
        }
        if (ObjectUtils.isNotEmpty(dictTypeDTO.getStatus())) {
            sqlBuilder.eq("status",dictTypeDTO.getStatus());
        }

        Page page = new Page();
        page.setPageNum(dictTypeDTO.getPageNum());
        page.setPageSize(dictTypeDTO.getPageSize());
        sqlBuilder.page(page);

        List<DictType> list = dictTypeRepo.select(sqlBuilder.build());

        page.setRows(list);

        return page;
    }

    @Override
    public DictType getById(String id) {
        DictType dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class)
                .eq("id", id)
                .build());

        return dictType;
    }
}
