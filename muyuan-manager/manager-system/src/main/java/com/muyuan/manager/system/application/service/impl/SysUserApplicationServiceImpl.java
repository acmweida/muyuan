package com.muyuan.manager.system.application.service.impl;

import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.system.application.service.SysUserApplicationService;
import com.muyuan.manager.system.domains.model.SysRole;
import com.muyuan.manager.system.domains.model.SysUser;
import com.muyuan.manager.system.domains.service.SysMenuDomainService;
import com.muyuan.manager.system.domains.service.SysRoleDomainService;
import com.muyuan.manager.system.domains.service.SysUserDomainService;
import com.muyuan.manager.system.domains.vo.SysUserVO;
import com.muyuan.manager.system.interfaces.assembler.SysUserInfoAssembler;
import com.muyuan.user.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class SysUserApplicationServiceImpl implements SysUserApplicationService {

    private SysUserDomainService sysUserDomainService;

    private SysRoleDomainService sysRoleDomainService;

    private SysMenuDomainService sysMenuDomainService;

    @Override
    public UserDTO getUserByUsername(String username) {
        final Optional<SysUser> userInfo = sysUserDomainService.getByyUsername(username);
        if (!userInfo.isPresent()) {
            return null;
        }
        SysUser user = userInfo.get();
        Long id = user.getId();
        List<SysRole> sysRoles = getUserRoles(id);

        List<String> roleNames = sysRoles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX + item.getCode()).collect(Collectors.toList());
        // 默认角色
//        roleNames.add(SecurityConst.DEFAULT_ROLE);

        UserDTO userDTO = SysUserInfoAssembler.buildUserTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return userDTO;
    }

    @Override
    public Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes) {
        return sysMenuDomainService.selectMenuPermissionByRoleCodes(roleCodes);
    }

    private List<SysRole> getUserRoles(Long id) {
        return sysRoleDomainService.getRoleByUserId(id);
    }

    @Override
    public Optional<SysUserVO> getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        final Optional<SysUser> userInfo = sysUserDomainService.getByyId(userId);
        if (!userInfo.isPresent()) {
            log.info("userId :{} 未找到", userId);
            return Optional.empty();
        }
        List<String> roleCodes = SecurityUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleCodes(roleCodes);
        SysUserVO sysUserVO = SysUserInfoAssembler.buildUserVO(userInfo.get(), roleCodes, perms);
        return Optional.of(sysUserVO);
    }

    @Override
    public Optional<SysUserVO> get(Long id) {
        final Optional<SysUser> userInfo = sysUserDomainService.getByyId(id);
        if (!userInfo.isPresent()) {
            log.info("userId :{} 未找到", id);
            return Optional.empty();
        }

        List<SysRole> roles = sysRoleDomainService.getRoleByUserId(id);

        SysUserVO sysUserVO = SysUserInfoAssembler.buildUserVO(userInfo.get(), roles);
        return Optional.of(sysUserVO);
    }

}
