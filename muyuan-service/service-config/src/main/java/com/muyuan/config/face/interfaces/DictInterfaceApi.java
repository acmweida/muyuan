package com.muyuan.config.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.config.api.DictInterface;
import com.muyuan.config.api.dto.*;
import com.muyuan.config.entity.DictData;
import com.muyuan.config.entity.DictType;
import com.muyuan.config.face.dto.mapper.DictMapper;
import com.muyuan.config.service.DictService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName DictInterfaceApi
 * Description
 * @Author 2456910384
 * @Date 2022/10/14 9:58
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.CONFIG, version = "1.0"
        , interfaceClass = DictInterface.class,
        methods = {
            @Method(name = "addDictType",retries = 0)
        }
)
public class DictInterfaceApi implements DictInterface {

    private DictService dictService;

    private DictMapper mapper;

    @Override
    public Result<List<DictDataDTO>> getDictDataByType(DictQueryRequest request) {
        return ResultUtil.success(
                mapper.to(dictService.getByDictTypeName(mapper.toCommand(request)))
        );
    }

    @Override
    public Result<DictTypeDTO> getDictType(Long id) {
        Optional<DictType> handler = dictService.getType(id);

        return handler.map(mapper::to)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result<DictDataDTO> getDictData(Long id) {
        Optional<DictData> handler = dictService.getData(id);

        return handler.map(mapper::to)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result<Page<DictTypeDTO>> list(DictTypeQueryRequest request) {
        Page<DictType> list = dictService.list(mapper.toCommand(request));

        return ResultUtil.success( Page.copy(list,mapper.toTypeDTO(list.getRows())));
    }

    @Override
    public Result<Page<DictDataDTO>> list(DictQueryRequest request) {
        Page<DictData> list = dictService.list(mapper.toCommand(request));

        return ResultUtil.success( Page.copy(list,mapper.to(list.getRows())));
    }

    @Override
    public Result addDictType(DictTypeRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(dictService.checkUnique(new DictType.Identify(request.getType())))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST);
        }

        boolean flag = dictService.addDictType(mapper.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result addDictData(DictDataRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(dictService.checkUnique(new DictData.Identify(request.getValue(), request.getType())))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST);
        }

        boolean flag = dictService.addDictData(mapper.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result updateDictData(DictDataRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(dictService.checkUnique(new DictData.Identify(request.getId(),request.getValue(), request.getType())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = dictService.updateDictData(mapper.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result updateDictType(DictTypeRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(dictService.checkUnique(new DictType.Identify(request.getId(), request.getType())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = dictService.updateDictType(mapper.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteDictData(Long... ids) {
        if (dictService.deleteDictData(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

    @Override
    public Result deleteDictType(Long... ids) {
        if (dictService.deleteDictType(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
