package com.muyuan.manager.system.service.impl;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.dto.RoleQueryParams;
import com.muyuan.manager.system.service.RoleService;
import com.muyuan.user.api.RoleInterface;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import com.muyuan.user.api.dto.RoleRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName SysRoleDomainServiceImpl
 * Description 角色域服务
 * @Author 2456910384
 * @Date 2022/4/18 15:41
 * @Version 1.0
 */
@Component
@Slf4j
public class RoleServiceImpl implements RoleService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private RoleInterface roleInterface;

    @Override
    public Page<RoleDTO> list(RoleQueryParams params) {

        RoleQueryRequest request = RoleQueryRequest.builder()
                .name(params.getName())
                .status(params.getStatus())
                .platformType(params.getPlatformType() == null ? PlatformType.OPERATOR : PlatformType.trance(params.getPlatformType()))
                .build();

        if (params.enablePage()) {
            request.setPageNum(params.getPageNum());
            request.setPageSize(params.getPageSize());
        }

        Result<Page<RoleDTO>> data = roleInterface.list(request);

        return data.getData();
    }

    @Override
    public Result selectUser(Long roleId, Long[] userIds) {
        return roleInterface.selectUser(roleId,userIds);
    }

    @Override
    public Result cancelUser(Long roleId, Long[] userIds) {
        return roleInterface.cancelUser(roleId,userIds);
    }

    @Override
    public Result add(RoleRequest request) {
        return roleInterface.add(request);
    }

    @Override
    public Result update(RoleRequest request) {
        request.setOpt(SecurityUtils.getOpt());
        return roleInterface.updateRole(request);
    }

    @Override
    public Optional<RoleDTO> getById(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<RoleDTO> role = roleInterface.getRole(id_);
                    return ResultUtil.getOr(role,null);
                });
    }

    @Override
    public Result deleteById(Long... ids) {
        return roleInterface.deleteRole(ids);
    }
}
