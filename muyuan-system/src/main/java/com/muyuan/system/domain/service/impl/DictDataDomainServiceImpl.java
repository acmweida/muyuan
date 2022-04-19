package com.muyuan.system.domain.service.impl;

import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.query.DictDataQuery;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.domain.service.DictDataDomainService;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName DictDataDomainService
 * Description 字典数据领域服务
 * @Author 2456910384
 * @Date 2022/4/18 11:34
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class DictDataDomainServiceImpl implements DictDataDomainService {

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
    @Transactional
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
        
        dictDataRepo.insert(dictData);

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
