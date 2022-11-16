package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.manager.system.dto.PermissionParams;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.manager.system.service.PermissionService;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @ClassName PermissionServiceImpl
 * Description 权限
 * @Author 2456910384
 * @Date 2022/11/16 14:54
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private PermissionInterface  permissionInterface;

    @Override
    public Page<PermissionDTO> list(PermissionQueryParams params) {
        PermissionQueryRequest request = PermissionQueryRequest.builder()
                .business(params.getBusiness())
                .module(params.getModule())
                .status(params.getStatus())
                .type(params.getType())
                .resource(params.getResource())
                .platformType(PlatformType.trance(params.getPlatformType()))
                .build();
        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }


        Result<Page<PermissionDTO>> res = permissionInterface.list(request);


        return res.getData();
    }

    @Override
    public Result add(PermissionParams params) {
        return null;
    }
}
