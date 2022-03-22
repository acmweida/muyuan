package com.muyuan.system.application.service.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.mybatis.jdbc.crud.SqlBuilder;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.system.application.query.SysMenuQuery;
import com.muyuan.system.application.query.SysRoleQuery;
import com.muyuan.system.application.query.SysUserQuery;
import com.muyuan.system.application.service.SysUserService;
import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.domain.entity.SysUserEntity;
import com.muyuan.system.domain.factories.SysUserFactory;
import com.muyuan.system.domain.model.SysRole;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.repo.SysUserRepo;
import com.muyuan.system.interfaces.assembler.SysUserInfoAssembler;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import com.muyuan.system.interfaces.dto.SysUserDTO;
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
public class SysUserServiceImpl implements SysUserService {

    private SysUserQuery sysUserQuery;

    private SysRoleQuery sysRoleQuery;

    private SysMenuQuery sysMenuQuery;

    private SysUserRepo sysUserRepo;

    @Override
    public SysUserDTO getUserByUsername(String username) {
        final Optional<SysUser> userInfo = sysUserQuery.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return null;
        }
        SysUser user = userInfo.get();
        Long id = user.getId();
        List<SysRole> sysRoles = getUserRoles(id);

        List<String> roleNames = sysRoles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX + item.getName()).collect(Collectors.toList());
        // 默认角色
        roleNames.add(SecurityConst.DEFAULT_ROLE);

        SysUserDTO userDTO = SysUserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return userDTO;
    }

    private Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return sysMenuQuery.selectMenuPermissionByRoleNames(roleIds);
    }

    private List<SysRole> getUserRoles(Long id) {
        return sysRoleQuery.getRoleByUserId(id);
    }

    @Override
    public Optional<SysUserVO> getUserInfo() {
        Long userId = JwtUtils.getUserId();
        final Optional<SysUser> userInfo = sysUserQuery.getUserInfo(userId);
        if (!userInfo.isPresent()) {
            log.info("userId :{} 未找到", userId);
            return Optional.empty();
        }
        List<String> roleNames = JwtUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleNames(roleNames);
        SysUserVO sysUserVO = SysUserInfoAssembler.buildUserVO(userInfo.get(), roleNames, perms);
        return Optional.of(sysUserVO);
    }

    @Override
    public int add(RegisterDTO registerInfo) {
        sysUserQuery.getUserByUsername(registerInfo.getUsername());
        SysUser account = sysUserRepo.selectOne(new SqlBuilder(SysUser.class).select("id")
                .eq("username", registerInfo.getUsername())
                .build());
        if (null != account) {
            return 1;
        }

        SysUserEntity sysUserEntity = SysUserFactory.newSysUserEntity(registerInfo, sysUserRepo);

        if (sysUserEntity.save()) {
            return 0;
        }
        return -1;
    }
}
