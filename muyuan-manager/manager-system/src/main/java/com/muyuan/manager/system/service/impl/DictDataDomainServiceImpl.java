package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.DictQueryRequest;
import com.muyuan.manager.system.dto.DictDataDTO;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.domains.repo.DictDataRepo;
import com.muyuan.manager.system.service.DictDataDomainService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DictDataDomainServiceImpl implements DictDataDomainService {

    @DubboReference(group = ServiceTypeConst.CONFIG, version = "1.0")
    private DictInterface dictInterface;

    @Autowired
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
    public Page<DictData> page(DictDataDTO dictDataDTO) {

        Page<DictData> page = Page.<DictData>builder().pageNum(dictDataDTO.getPageNum())
                .pageSize(dictDataDTO.getPageSize()).build();

        List<DictData> list = dictDataRepo.select(dictDataDTO, page);

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
    public List<com.muyuan.config.api.dto.DictDataDTO> getByDataType(String dictDataType) {

        if (StringUtils.isBlank(dictDataType)) {
            return Collections.EMPTY_LIST;
        }

        Result<List<com.muyuan.config.api.dto.DictDataDTO>> resultHandle = dictInterface.getDictDataByType(DictQueryRequest.builder()
                .dictTypeName(dictDataType)
                .build());

        return ResultUtil.getOr(resultHandle, () -> GlobalConst.EMPTY_LIST);
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
