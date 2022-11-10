package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.DictTypeDTO;
import com.muyuan.config.api.dto.DictTypeQueryRequest;
import com.muyuan.config.api.dto.DictTypeRequest;
import com.muyuan.manager.system.dto.DictTypeParams;
import com.muyuan.manager.system.dto.DictTypeQueryParams;
import com.muyuan.manager.system.service.DictTypeService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictTypeControlerImpl
 * Description 指点类型
 * @Author 2456910384
 * @Date 2022/3/31 11:43
 * @Version 1.0
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {


    @DubboReference(group = ServiceTypeConst.CONFIG, version = "1.0")
    private DictInterface dictInterface;

    // ##############################  query ########################## //

    /**
     * 通过DataType 查询字典数据
     *
     * @param params
     * @return
     */
    @Override
    public Page<DictTypeDTO> list(DictTypeQueryParams params) {

        DictTypeQueryRequest request = DictTypeQueryRequest.builder()
                .name(params.getName())
                .type(params.getType())
                .status(params.getStatus())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }


        Result<Page<DictTypeDTO>> res = dictInterface.list(request);


        return res.getData();
    }

    @Override
    public List<DictTypeDTO> selectDictTypeAll() {
        Result<Page<DictTypeDTO>> list = dictInterface.list(DictTypeQueryRequest.builder().build());
        return list.getData().getRows();
    }


    /**
     * 字典类类型详情查询
     *
     * @param id
     * @return
     */
    @Override
    public Optional<DictTypeDTO> getById(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<DictTypeDTO> dictType = dictInterface.getDictType(id_);
                    return ResultUtil.getOr(dictType,null);
                });
    }

    @Override
    public Result update(DictTypeParams dictTypeParams) {
        return dictInterface.updateDictType(DictTypeRequest.builder()
                .type(dictTypeParams.getType())
                .status(dictTypeParams.getStatus())
                .remark(dictTypeParams.getRemark())
                .id(dictTypeParams.getId())
                .updateBy(SecurityUtils.getUserId())
                .name(dictTypeParams.getName())
                .build());
    }

    // ##############################  query ########################## //

    @Override
    public Result add(DictTypeParams dictTypeParams) {

        return dictInterface.addDictType(DictTypeRequest.builder()
                .name(dictTypeParams.getName())
                .type(dictTypeParams.getType())
                .status(dictTypeParams.getStatus())
                .createBy(SecurityUtils.getUserId())
                .build());
    }

    @Override
    public Result deleteById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }

        return  dictInterface.deleteDictType(ids);
    }

}
