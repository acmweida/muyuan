package com.muyuan.member.interfaces.facade.controller.impl;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.domain.query.UserQuery;
import com.muyuan.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.dto.UserDTO;
import com.muyuan.member.interfaces.facade.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserControllerImpl implements UserController {

    @Autowired
    UserQuery userQuery;

    @Override
    public Result getUserInfo() {
        final Optional<User> userInfo = userQuery.getUserInfo("xxxx");
        if (!userInfo.isPresent()) {
            return ResultUtil.renderFail("用户信息不存在");
        }
        UserVO userVO = UserInfoAssembler.buildUserVO(userInfo.get());
        return ResultUtil.render(userVO);
    }

    @Override
    public Result<UserDTO> getUserByAccount(String account) {
        final Optional<User> userInfo = userQuery.getUserByAccount(account);
        if (!userInfo.isPresent()) {
            return ResultUtil.renderFail("用户信息不存在");
        }
        UserDTO userDTO = UserInfoAssembler.buildUserDTO(userInfo.get());
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
