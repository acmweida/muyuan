package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.dto.PermissionDTO;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.api.dto.PermissionRequest;
import com.muyuan.user.domain.model.entity.Permission;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.service.PermissionService;
import com.muyuan.user.domain.service.RoleService;
import com.muyuan.user.face.dto.mapper.PermissionMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.*;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  权限
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = PermissionInterface.class,
        methods = {
                @Method(name = "add", retries = 0)
        }
)
public class PermissionInterfaceApi implements PermissionInterface {

    private PermissionMapper PERMISSION_MAPPER;

    private PermissionService permissionService;

    private RoleService roleService;

    @Override
    public Result<Set<String>> getPermissionByUserID(Long userId, PlatformType platformType) {

        List<Role> roles = roleService.selectRoleByUserId(new UserID(userId), platformType);

        List<Permission> permissions = permissionService.getPermissionByRoles(roles.toArray(new Role[0]));

        Set<String> parms = new HashSet<>();
        for (Permission permission : permissions) {
            parms.add(permission.getPerms());
        }

        return ResultUtil.success(parms);
    }

    @Override
    public Result<Set<String>> getPermissionByRoleCodes(List<String> roleCode, PlatformType platformType) {
        List<Role> roles = new ArrayList<>();
        for (String role : roleCode) {
            roles.add(Role.builder().code(role)
                    .platformType(platformType)
                    .build());
        }

        List<Permission> permissions = permissionService.getPermissionByRoles(roles.toArray(new Role[0]));

        Set<String> parms = new HashSet<>();
        for (Permission permission : permissions) {
            parms.add(permission.getPerms());
        }

        return ResultUtil.success(parms);
    }

    @Override
    public Result<List<PermissionDTO>> getPermissionByRoleID(Long roleId) {
        List<Permission> permissions = permissionService.getPermissionByRoleID(new RoleID(roleId));

        return ResultUtil.success(PERMISSION_MAPPER.toDTO(permissions));
    }

    @Override
    public Result<Page<PermissionDTO>> list(PermissionQueryRequest request) {
        Page<Permission> list = permissionService.list(PERMISSION_MAPPER.toCommand(request));

        return ResultUtil.success(Page.copy(list, PERMISSION_MAPPER.toDTO(list.getRows())));
    }

    @Override
    public Result add(PermissionRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(permissionService.checkUnique(new Permission.Identify(request.getPerms())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = permissionService.addPermission(PERMISSION_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<PermissionDTO> getPermission(Long id) {
        Optional<Permission> handler = permissionService.getPermission(id);

        return handler.map(PERMISSION_MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updatePermission(PermissionRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(permissionService.checkUnique(new Permission.Identify(
                request.getId(),
                request.getPerms())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }

        boolean flag = permissionService.updatePermission(PERMISSION_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deletePermission(Long... ids) {
        if (permissionService.deletePermissionById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
