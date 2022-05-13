package com.muyuan.system.domain.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.system.domain.model.DictData;
import com.muyuan.system.domain.repo.DictDataRepo;
import com.muyuan.system.domain.service.DictDataDomainService;
import com.muyuan.system.interfaces.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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

    private DictDataRepo dictDataRepo;

    // ##############################  query ########################## //

    @Override
    public String checkUnique(DictData dictData) {
        Long id = null == dictData.getId() ? 0 : dictData.getId();
        dictData = dictDataRepo.selectOne(new SqlBuilder(DictData.class).select("id")
                .eq("type", dictData.getType())
                .eq("label", dictData.getLabel())
                .eq("value", dictData.getValue())
                .build());
        if (null != dictData && !dictData.getId().equals(id)) {
            return GlobalConst.NOT_UNIQUE;
        }
        return GlobalConst.UNIQUE;
    }

    /**
     * 查询字典数据
     *
     * @param dictDataDTO
     * @return
     */
    @Override
    public Page list(DictDataDTO dictDataDTO) {

        SqlBuilder sqlBuilder = new SqlBuilder(DictData.class)
                .eq("type", dictDataDTO.getType())
                .eq("status", dictDataDTO.getStatus())
                .orderByDesc("updateTime", "createTime");

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
     *
     * @param dictDataType
     * @return
     */
    @Override
    public List<DictData> getByDataType(String dictDataType) {

        if (StringUtils.isBlank(dictDataType)) {
            return Collections.EMPTY_LIST;
        }

        List<DictData> list = dictDataRepo.selectByDateType(dictDataType);

        return list;
    }

    // ##############################  query ########################## //

    @Override
    @Transactional
    public void add(DictDataDTO dictDataDTO) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDTO, dictData);
        dictDataRepo.insert(dictData);
    }

    @Override
    public void deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        dictDataRepo.delete(ids);
    }

}
