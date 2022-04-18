package com.muyuan.member.application.service.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.query.RoleQuery;
import com.muyuan.member.application.service.UserApplicationService;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.service.UserDomainService;
import com.muyuan.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.member.interfaces.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserApplicationServiceImpl implements UserApplicationService {

    private UserDomainService userDomainService;

    private RoleQuery sysRoleQuery;

    private MenuQuery sysMenuQuery;

    @Override
    public UserDTO getUserByUsername(String username) {
        final Optional<User> userInfo = userDomainService.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return null;
        }
        User user = userInfo.get();
        Long id = user.getId();
        List<Role> sysRoles = getUserRoles(id);

        List<String> roleNames = sysRoles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX+item.getName()).collect(Collectors.toList());

        UserDTO userDTO = UserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return userDTO;
    }

    @Override
    public Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return sysMenuQuery.selectMenuPermissionByRoleNames(roleIds);
    }

    private List<Role> getUserRoles(Long id) {
        return  sysRoleQuery.getRoleByUserId(id);
    }
}
