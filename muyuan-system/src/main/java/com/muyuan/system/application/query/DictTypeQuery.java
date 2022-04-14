package com.muyuan.system.application.query;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictType;
import com.muyuan.system.domain.repo.DictTypeRepo;
import com.muyuan.system.interfaces.dto.DictTypeDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DictTypeQuery {

    private DictTypeRepo dictTypeRepo;

    /**
     * 通过DataType 查询字典数据
     * @param dictTypeDTO
     * @return
     */
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

    /**
     * 字典类类型详情查询
     * @param id
     * @return
     */
    public DictType getById(String id) {
        DictType dictType = dictTypeRepo.selectOne(new SqlBuilder(DictType.class)
                .eq("id", id)
                .build());

        return dictType;
    }
}
