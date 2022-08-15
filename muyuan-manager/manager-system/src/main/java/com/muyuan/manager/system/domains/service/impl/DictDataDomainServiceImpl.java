package com.muyuan.manager.system.domains.service.impl;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.domains.repo.DictDataRepo;
import com.muyuan.manager.system.domains.service.DictDataDomainService;
import com.muyuan.manager.system.domains.dto.DictDataDTO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        dictData = dictDataRepo.selectOne(dictData);
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
    public Page page(DictDataDTO dictDataDTO) {

        Page page = Page.builder().pageNum(dictDataDTO.getPageNum())
                .pageSize(dictDataDTO.getPageSize()).build();

        List<DictData> list = dictDataRepo.select(dictDataDTO,page);

        page.setRows(list);

        return page;
    }

    /**
     * 查询字典数据
     *
     * @param dictDataDTO
     * @return
     */
    @Override
    public List<DictData> list(DictDataDTO dictDataDTO) {

        List<DictData> list = dictDataRepo.select(dictDataDTO);
        return list;
    }

    @Override
    public Optional<DictData> get(DictDataDTO dictDataDTO) {
        return Optional.ofNullable(
                dictDataRepo.selectOne(dictDataDTO.convert())
        );
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

        List<DictData> list = dictDataRepo.selectByDataType(dictDataType);

        return list;
    }

    // ##############################  query ########################## //

    @Override
    @Transactional
    public void add(DictDataDTO dictDataDTO) {
        DictData dictData = dictDataDTO.convert();
        dictDataRepo.insert(dictData);
    }

    @Override
    public void update(DictDataDTO dictDataDTO) {
        DictData dictData = dictDataDTO.convert();
        dictDataRepo.update(dictData);
    }

    @Override
    public void deleteById(String... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return;
        }
        dictDataRepo.delete(ids);
    }

}
