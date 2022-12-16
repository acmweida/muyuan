package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.manager.system.dto.OperLogQueryParams;
import com.muyuan.manager.system.service.OperLogService;
import com.muyuan.user.api.OperLogInterface;
import com.muyuan.user.api.dto.OperLogDTO;
import com.muyuan.user.api.dto.OperLogQueryRequest;
import com.muyuan.user.api.dto.OperLogRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName OperLogServiceImpl
 * Description 操作日志记录
 * @Author  ${author}
 * @Date 2022-12-15T15:27:12.638+08:00
 * @Version 1.0
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private OperLogInterface operLogInterface;

    @Override
    public Page<OperLogDTO> list(OperLogQueryParams params) {
        OperLogQueryRequest request = OperLogQueryRequest.builder()
                .id(params.getId())
                .title(params.getTitle())
                .businessType(params.getBusinessType())
                .method(params.getMethod())
                .requestMethod(params.getRequestMethod())
                .operatorType(params.getOperatorType())
                .operName(params.getOperName())
                .deptName(params.getDeptName())
                .operUrl(params.getOperUrl())
                .operIp(params.getOperIp())
                .operLocation(params.getOperLocation())
                .operParam(params.getOperParam())
                .jsonResult(params.getJsonResult())
                .status(params.getStatus())
                .errorMsg(params.getErrorMsg())
                .operTime(params.getOperTime())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<OperLogDTO>> res = operLogInterface.list(request);

        return res.getData();
    }

    @Override
    public Result add(OperLogRequest request) {
        return operLogInterface.add(request);
    }

    @Override
    public Optional<OperLogDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<OperLogDTO> operLogHander = operLogInterface.getOperLog(id_);
                    return ResultUtil.getOr(operLogHander, null);
                });
    }

    @Override
    public Result deleteById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }

        return operLogInterface.deleteOperLog(ids);
    }
}
