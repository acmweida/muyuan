package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.RoleInterface;
import com.muyuan.user.api.dto.RoleDTO;
import com.muyuan.user.api.dto.RoleQueryRequest;
import com.muyuan.user.domain.model.entity.Role;
import com.muyuan.user.domain.service.RoleDomainService;
import com.muyuan.user.face.dto.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = RoleInterface.class
)
public class RoleInterfaceApi implements RoleInterface {

    private RoleDomainService roleDomainService;

    private RoleMapper roleMapper;

    @Override
    public Result<Page<RoleDTO>> list(RoleQueryRequest request) {
        Page<Role> res = roleDomainService.list(roleMapper.toCommand(request));

        return ResultUtil.success(Page.copy(res, roleMapper.toDTO(res.getRows())));
    }

}
