package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.RoleInterface;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import com.muyuan.user.api.dto.RoleRequest;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.service.RoleService;
import com.muyuan.user.face.dto.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.Optional;

@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = RoleInterface.class,
        methods = {
        @Method(name = "add",retries = 0)
    }
)
public class RoleInterfaceApi implements RoleInterface {

    private RoleService roleService;

    private RoleMapper ROLE_MAPPER;

    @Override
    public Result<Page<RoleDTO>> list(RoleQueryRequest request) {
        Page<Role> res = roleService.list(ROLE_MAPPER.toCommand(request));

        return ResultUtil.success(Page.copy(res, ROLE_MAPPER.toDTO(res.getRows())));
    }

    @Override
    public Result<RoleDTO> getRole(Long id) {
        Optional<Role> handler = roleService.getRoleById(id);

        return handler.map(ROLE_MAPPER::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result add(RoleRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(roleService.checkUnique(new Role.Identify(request.getPlatformType(),request.getCode())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = roleService.addRole(ROLE_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result updateRole(RoleRequest request) {
        if (GlobalConst.NOT_UNIQUE.equals(roleService.checkUnique(new Role.Identify(new RoleID(request.getId())
                ,request.getPlatformType(),request.getCode())))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = roleService.updateRole(ROLE_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteRole(Long... ids) {
        if (roleService.deleteRoleById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

}
