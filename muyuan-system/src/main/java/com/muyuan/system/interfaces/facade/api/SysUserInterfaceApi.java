package com.muyuan.system.interfaces.facade.api;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.system.api.SysUserInterface;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.query.SysRoleQuery;
import com.muyuan.system.domain.query.SysUserQuery;
import com.muyuan.system.interfaces.assembler.SysUserInfoAssembler;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@Service(group = ServiceTypeConst.SYSTEM_SERVICE,version = "1.0")
public class SysUserInterfaceApi implements SysUserInterface {

    @Autowired
    SysUserQuery sysUserQuery;

    @Autowired
    SysRoleQuery sysRoleQuery;


    @Override
    public Result<SysUserDTO> getUserByUsername(String username) {
        final Optional<SysUser> userInfo = sysUserQuery.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        SysUser user = userInfo.get();
        Long id = user.getId();
        List<SysRole> sysRoles = getUserRoles(id);

        List<String> roleNames = sysRoles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX+item.getName()).collect(Collectors.toList());

        SysUserDTO userDTO = SysUserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return ResultUtil.success(userDTO);
    }

    private List<SysRole> getUserRoles(Long id) {
        return  sysRoleQuery.getRoleByUserId(id);
    }
}
