package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.OperLogInterface;
import com.muyuan.user.api.dto.OperLogDTO;
import com.muyuan.user.api.dto.OperLogQueryRequest;
import com.muyuan.user.api.dto.OperLogRequest;
import com.muyuan.user.domain.model.entity.OperLog;
import com.muyuan.user.domain.service.OperLogService;
import com.muyuan.user.face.dto.mapper.OperLogMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.Optional;

/**
 * @ClassName OperLogInterfaceApi
 * Description 内部接口  操作日志记录
 * @author ${author}
 * @date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = OperLogInterface.class,
        methods = {
                @Method(name = "add",retries = 0)
        }
)
public class OperLogInterfaceApi implements OperLogInterface {

    private OperLogMapper MAPPER;

    private OperLogService operLogService;

    @Override
    public Result<Page<OperLogDTO>> list(OperLogQueryRequest request) {
        Page<OperLog> list = operLogService.list(MAPPER.toCommand(request));

        return ResultUtil.success( Page.copy(list,MAPPER.toDTO(list.getRows())));
    }

    @Override
    public Result add(OperLogRequest request) {
        boolean flag = operLogService.addOperLog(MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<OperLogDTO> getOperLog(Long id) {
        Optional<OperLog> handler = operLogService.getOperLog(id);

        return handler.map(MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result deleteOperLog(Long... ids) {
        if (operLogService.deleteOperLogById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
