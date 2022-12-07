package com.muyuan.store.system.application.service.impl;

import com.muyuan.common.core.constant.SecurityConst;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.store.system.domains.dto.RegisterDTO;
import com.muyuan.store.system.domains.factories.UserFactory;
import com.muyuan.store.system.domains.model.Role;
import com.muyuan.store.system.domains.model.User;
import com.muyuan.store.system.domains.service.MenuDomainService;
import com.muyuan.store.system.domains.service.RoleDomainService;
import com.muyuan.store.system.domains.service.UserDomainService;
import com.muyuan.store.system.domains.vo.UserVO;
import com.muyuan.store.system.application.service.UserApplicationService;
import com.muyuan.store.system.interfaces.assembler.UserInfoAssembler;
import com.muyuan.user.api.dto.OperatorDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public OperatorDTO getUserByUsername(String username) {
        final Optional<User> userInfo = userDomainService.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return null;
        }
        User user = userInfo.get();
        Long id = user.getId();
        List<Role> sysRoles = getUserRoles(id);

        List<String> roleCodes = sysRoles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX+item.getCode()).collect(Collectors.toList());

        OperatorDTO userTO = UserInfoAssembler.buildUserTO(userInfo.get());
//        userTO.setRoles(roleCodes);
        return userTO;
    }

    @Override
    public Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes) {
        return menuDomainService.selectMenuPermissionByRoleCodes(roleCodes);
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
        List<String> roleCodes = SecurityUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleCodes(roleCodes);
        UserVO userVO = UserInfoAssembler.buildUserVO(userInfo.get(), roleCodes, perms);

        return Optional.of(userVO);
    }

    /**
     * 账户注册
     *
     * @param register
     * @return
     */
    @Transactional
    public void add(RegisterDTO register) {

        User user = UserFactory.newUserEntity(register);
        userDomainService.add(user);

        Optional<Role> role = roleDomainService.get(Role.builder()
                .code(SecurityConst.SHOP_KEEPER_ROLE_CODE)
                .build());
        role.ifPresent(
                item -> userDomainService.addRole(user,item)
        );
    }


}
