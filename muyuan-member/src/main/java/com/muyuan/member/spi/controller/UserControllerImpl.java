package com.muyuan.member.spi.controller;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.service.UserService;
import com.muyuan.member.spi.UserController;
import com.muyuan.member.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    public Result getUserInfo() {
        final Optional<UserVO> userInfo = userService.getUserInfo("xxxx");
        if (!userInfo.isPresent()) {
            return ResultUtil.renderFail("用户信息不存在");
        }
        return ResultUtil.render(userInfo.get());
    }

    public Result accountRegister(RegisterDTO register) {
        int registerResult = userService.accountRegister(register);
        if (registerResult == 0) {
            return   ResultUtil.render("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.renderFail("账号已存在");
        }
        return ResultUtil.renderFail("注册失败");
    }
}
