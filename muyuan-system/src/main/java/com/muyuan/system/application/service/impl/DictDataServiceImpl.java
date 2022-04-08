package com.muyuan.system.application.service.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.application.query.DictDataQuery;
import com.muyuan.system.application.service.DictDataService;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DictDataServiceImpl
 * Description 字典数据
 * @Author 2456910384
 * @Date 2022/4/7 11:15
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    private DictDataQuery dictTypeQuery;

    private DictDataRepo dictDataRepo;

    @Override
    public Page list(DictDataDTO dictDataDTO) {
        return dictTypeQuery.list(dictDataDTO);
    }

    @Override
    public List<DictData> getByDataType(String dictType) {
        return dictTypeQuery.getByDataType(dictType);
    }

    @Override
    public Integer add(DictDataDTO dictDataDTO) {
        DictData dictData = dictDataRepo.selectOne(new SqlBuilder(DictData.class)
                .eq("type", dictDataDTO.getType())
                .eq("label", dictDataDTO.getLabel())
                .eq("value", dictDataDTO.getValue())
                .build());
        if (null != dictData) {
            return 1;
        }

        dictData = new DictData();
        BeanUtils.copyProperties(dictDataDTO, dictData);
        if (dictDataRepo.insert(dictData)) {
            return 0;
        }

        return 1;
    }

    @Override
    public boolean deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return true;
        }
        return dictDataRepo.delete(ids);
    }
}
