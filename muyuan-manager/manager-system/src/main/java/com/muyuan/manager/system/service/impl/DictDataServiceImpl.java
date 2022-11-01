package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictQueryRequest;
import com.muyuan.manager.system.domains.model.DictData;
import com.muyuan.manager.system.domains.repo.DictDataRepo;
import com.muyuan.manager.system.dto.DictDataQueryParams;
import com.muyuan.manager.system.dto.DictDataRequest;
import com.muyuan.manager.system.service.DictDataService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DictDataServiceImpl implements DictDataService {

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
     * @param params
     * @return
     */
    @Override
    public Page<DictDataDTO> page(DictDataQueryParams params) {

        DictQueryRequest request = DictQueryRequest.builder()
                .label(params.getLabel())
                .type(params.getType())
                .status(params.getStatus())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }


        Result<Page<DictDataDTO>> res = dictInterface.list(request);


        return res.getData();
    }

    /**
     * 查询字典数据
     *
     * @param dictDataRequest
     * @return
     */
    @Override
    public List<DictData> list(DictDataRequest dictDataRequest) {

        List<DictData> list = dictDataRepo.select(dictDataRequest);
        return list;
    }

    @Override
    public Optional<DictDataDTO> getById(Long id) {

        return Optional.of(id)
                .map(id_ -> {
                    Result<DictDataDTO> dictType = dictInterface.getDictData(id_);
                    return ResultUtil.getOr(dictType,null);
                });
    }

    /**
     * 通过DataType 查询字典数据
     *
     * @param dictDataType
     * @return
     */
    @Override
    public List<DictDataDTO> getByDataType(String dictDataType) {

        if (StringUtils.isBlank(dictDataType)) {
            return Collections.EMPTY_LIST;
        }

        Result<List<DictDataDTO>> resultHandle = dictInterface.getDictDataByType(DictQueryRequest.builder()
                .type(dictDataType)
                .build());

        return ResultUtil.getOr(resultHandle, () -> GlobalConst.EMPTY_LIST);
    }

    // ##############################  query ########################## //

    @Override
    public Result add(DictDataRequest dictDataRequest) {
        return dictInterface.addDictData(com.muyuan.config.api.dto.DictDataRequest.builder()
                .label(dictDataRequest.getLabel())
                .type(dictDataRequest.getType())
                .value(dictDataRequest.getValue())
                .status(dictDataRequest.getStatus())
                .createBy(SecurityUtils.getUserId())
                .cssClass(dictDataRequest.getCssClass())
                .listClass(dictDataRequest.getListClass())
                .orderNum(dictDataRequest.getOrderNum())
                .remark(dictDataRequest.getRemark())
                .build());
    }

    @Override
    public void update(DictDataRequest dictDataRequest) {
        DictData dictData = new DictData();
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
