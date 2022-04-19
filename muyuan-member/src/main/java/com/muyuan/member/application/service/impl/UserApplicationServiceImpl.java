package com.muyuan.member.application.service.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.member.application.service.UserApplicationService;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.service.MenuDomainService;
import com.muyuan.member.domain.service.RoleDomainService;
import com.muyuan.member.domain.service.UserDomainService;
import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.member.interfaces.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserApplicationServiceImpl implements UserApplicationService {

    private UserDomainService userDomainService;

    private RoleDomainService roleDomainService;

    private MenuDomainService menuDomainService;

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
        return menuDomainService.selectMenuPermissionByRoleNames(roleIds);
    }

    private List<Role> getUserRoles(Long id) {
        return  roleDomainService.getRoleByUserId(id);
    }

    @Override
    public Optional<UserVO> getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        final Optional<User> userInfo = userDomainService.getByyId(userId);
        if (!userInfo.isPresent()) {
            log.info("userId :{} 未找到", userId);
            return Optional.empty();
        }
        List<String> roleNames = SecurityUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleNames(roleNames);
        UserVO userVO = UserInfoAssembler.buildUserVO(userInfo.get(), roleNames, perms);
        return Optional.of(userVO);
    }
}
