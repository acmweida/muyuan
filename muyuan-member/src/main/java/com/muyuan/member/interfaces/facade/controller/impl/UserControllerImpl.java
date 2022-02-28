package com.muyuan.member.interfaces.facade.controller.impl;

import com.muyuan.common.core.constant.auth.SecurityConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.query.RoleQuery;
import com.muyuan.member.domain.query.UserQuery;
import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import com.muyuan.member.interfaces.dto.UserDTO;
import com.muyuan.member.interfaces.facade.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserControllerImpl implements UserController {

    @Autowired
    UserQuery userQuery;

    @Autowired
    RoleQuery roleQuery;

    @Autowired
    MenuQuery menuQuery;

    @Override
    public Result<UserVO> getUserInfo() {
        Long userId = JwtUtils.getUserId();
        final Optional<User> userInfo = userQuery.getUserInfo(userId);
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        List<String> roleNames = JwtUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleNames(roleNames);
        UserVO userVO = UserInfoAssembler.buildUserVO(userInfo.get(),roleNames,perms);
        return ResultUtil.success(userVO);
    }

    private List<Role> getUserRoles(Long id) {
          return  roleQuery.getRoleByUserId(id);
    }

    private Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return menuQuery.selectMenuPermissionByRoleNames(roleIds);
    }

    @Override
    public Result<UserDTO> getUserByUsername(String username) {
        final Optional<User> userInfo = userQuery.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        User user = userInfo.get();
        Long id = user.getId();
        List<Role> roles = getUserRoles(id);

        List<String> roleNames = roles.stream().map(item -> SecurityConst.AUTHORITY_PREFIX+item.getName()).collect(Collectors.toList());

        UserDTO userDTO = UserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return ResultUtil.success(userDTO);
    }

    public Result accountRegister(RegisterDTO register) {
        int registerResult = userQuery.accountRegister(register);
        if (registerResult == 0) {
            return   ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }
}
