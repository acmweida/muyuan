package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
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
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDTO, dictData);

        return dictDataRepo.insert(dictData);
    }



    @Override
    public int deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return 0;
        }
        return dictDataRepo.delete(ids);
    }

    @Override
    public String checkUnique(DictData dictData) {
        Long id = null == dictData.getId() ? 0 : dictData.getId();
        dictData = dictDataRepo.selectOne(new SqlBuilder(DictData.class).select("id")
                .eq("type", dictData.getType())
                .eq("label", dictData.getLabel())
                .eq("value", dictData.getValue())
                .build());
        if (null != dictData && dictData.getId().equals(id)) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

}
