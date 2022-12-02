package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.manager.system.dto.OperatorQueryParams;
import com.muyuan.manager.system.service.OperatorService;
import com.muyuan.user.api.OperatorInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import com.muyuan.user.api.dto.OperatorRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class OperatorServiceImpl implements OperatorService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private OperatorInterface operatorInterface;

    @Override
    public  Page<OperatorDTO> list(OperatorQueryParams params) {

        OperatorQueryRequest request = OperatorQueryRequest.builder()
                .status(params.getStatus())
                .username(params.getUsername())
                .phone(params.getPhone())
                .platformType(PlatformType.trance(params.getPlatformType()))
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<OperatorDTO>> res = operatorInterface.list(request);

        return res.getData();
    }

    @Override
    public Page<OperatorDTO> selectAllocatedList(OperatorQueryParams params) {
        OperatorQueryRequest request = OperatorQueryRequest.builder()
                .username(params.getUsername())
                .phone(params.getPhone())
                .roleId(params.getRoleId())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<OperatorDTO>> res = operatorInterface.selectAllocatedList(request);

        return res.getData();
    }

    @Override
    public Page<OperatorDTO> selectUnallocatedList(OperatorQueryParams params) {
        OperatorQueryRequest request = OperatorQueryRequest.builder()
                .username(params.getUsername())
                .phone(params.getPhone())
                .roleId(params.getRoleId())
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<OperatorDTO>> res = operatorInterface.selectUnallocatedList(request);

        return res.getData();
    }

    @Override
    public Optional<OperatorDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<OperatorDTO> permissioHander = operatorInterface.get(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    @Override
    public Result add(OperatorRequest request) {
        return operatorInterface.add(request);
    }

}
