package com.muyuan.system.application.service.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.system.application.service.SysUserService;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.application.query.SysMenuQuery;
import com.muyuan.system.application.query.SysRoleQuery;
import com.muyuan.system.application.query.SysUserQuery;
import com.muyuan.system.interfaces.assembler.SysUserInfoAssembler;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户聚合服务
 */
@Component
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {

    private SysUserQuery sysUserQuery;

    private SysRoleQuery sysRoleQuery;

    private SysMenuQuery sysMenuQuery;

    @Override
    public SysUserDTO getUserByUsername(String username) {
        final Optional<SysUser> userInfo = sysUserQuery.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return null;
        }
        SysUser user = userInfo.get();
        Long id = user.getId();
        List<SysRole> sysRoles = getUserRoles(id);

        List<String> roleNames = sysRoles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX+item.getName()).collect(Collectors.toList());

        Set<String> perms = getMenuPermissionByRoleNames(roleNames);

        SysUserDTO userDTO = SysUserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        userDTO.setPermissions(new ArrayList<>(perms));
        return userDTO;
    }

    private Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return sysMenuQuery.selectMenuPermissionByRoleNames(roleIds);
    }

    private List<SysRole> getUserRoles(Long id) {
        return  sysRoleQuery.getRoleByUserId(id);
    }
}
