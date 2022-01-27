package com.muyuan.member.interfaces.facade.controller.impl;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.common.util.JwtUtils;
import com.muyuan.member.domain.model.Role;
import com.muyuan.member.domain.model.User;
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
import java.util.stream.Collectors;

@Component
public class UserControllerImpl implements UserController {

    @Autowired
    UserQuery userQuery;

    @Autowired
    RoleQuery roleQuery;

    @Override
    public Result<UserVO> getUserInfo() {
        Long userId = JwtUtils.getUserId();
        final Optional<User> userInfo = userQuery.getUserInfo(userId);
        if (!userInfo.isPresent()) {
            return ResultUtil.renderFail("用户信息不存在");
        }
        List<Role> roles = getUserRoles(userId);
        UserVO userVO = UserInfoAssembler.buildUserVO(userInfo.get(),roles);
        return ResultUtil.render(userVO);
    }

    private List<Role> getUserRoles(Long id) {
          return  roleQuery.getRoleByUserId(id);
    }

    @Override
    public Result<UserDTO> getUserByUsername(String username) {
        final Optional<User> userInfo = userQuery.getUserByUsername(username);
        if (!userInfo.isPresent()) {
            return ResultUtil.renderFail("用户信息不存在");
        }
        User user = userInfo.get();
        Long id = user.getId();
        List<Role> roles = getUserRoles(id);

        List<String> roleNames = roles.stream().map(item -> item.getName()).collect(Collectors.toList());

        UserDTO userDTO = UserInfoAssembler.buildUserDTO(userInfo.get());
        userDTO.setRoles(roleNames);
        return ResultUtil.render(userDTO);
    }

    public Result accountRegister(RegisterDTO register) {
        int registerResult = userQuery.accountRegister(register);
        if (registerResult == 0) {
            return   ResultUtil.render("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.renderFail("账号已存在");
        }
        return ResultUtil.renderFail("注册失败");
    }
}
