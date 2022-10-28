package com.muyuan.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.config.api.dto.DictDataRequest;
import com.muyuan.config.api.dto.DictQueryRequest;
import com.muyuan.system.dto.DictDataQueryParams;
import com.muyuan.system.service.DictDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

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
public class DictDataServiceImpl implements DictDataService {

    @DubboReference(group = ServiceTypeConst.CONFIG, version = "1.0")
    private DictInterface dictInterface;


    // ##############################  query ########################## //

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



}
