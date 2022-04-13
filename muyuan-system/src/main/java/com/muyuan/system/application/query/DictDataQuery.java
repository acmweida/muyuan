package com.muyuan.system.application.query;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class DictDataQuery {

    private DictDataRepo dictDataRepo;

    /**
     * 查询字典数据
     * @param dictDataDTO
     * @return
     */
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

    /**
     * 通过DataType 查询字典数据
     * @param dictDataType
     * @return
     */
    public List<DictData> getByDataType(String dictDataType) {

        if (StringUtils.isBlank(dictDataType)) {
            return Collections.EMPTY_LIST;
        }

        List<DictData> list = dictDataRepo.select(new SqlBuilder(DictData.class)
                .eq("type", dictDataType)
                .eq("status", 0)
                .build());

        return list;
    }

}
