package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.manager.system.dto.PermissionQueryParams;
import com.muyuan.manager.system.service.PermissionService;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.api.dto.PermissionRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private PermissionInterface permissionInterface;

    @Override
    public Page<PermissionDTO> list(PermissionQueryParams params) {
        PermissionQueryRequest request = PermissionQueryRequest.builder()
                .business(params.getBusiness())
                .module(params.getModule())
                .status(params.getStatus())
                .type(params.getType())
                .types(params.getTypes())
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
    public List<PermissionDTO> getByRoleId(Long roleId) {
        if (ObjectUtils.isEmpty(roleId)) {
            return GlobalConst.EMPTY_LIST;
        }

        Result<List<PermissionDTO>> permissioHander = permissionInterface.getPermissionByRoleID(roleId);

        return ResultUtil.getOr(permissioHander, () -> {
            return GlobalConst.EMPTY_LIST;
        });
    }

    @Override
    public Result add(PermissionRequest request) {
        return permissionInterface.add(request);
    }

    @Override
    public Optional<PermissionDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<PermissionDTO> permissioHander = permissionInterface.getPermission(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    @Override
    public Result update(PermissionRequest request) {
        return permissionInterface.updatePermission(request);
    }

    @Override
    public Result deleteById(Long... ids) {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }

        return permissionInterface.deletePermission(ids);
    }
}
