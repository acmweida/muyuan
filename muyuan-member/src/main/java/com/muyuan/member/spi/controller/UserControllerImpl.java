package com.muyuan.member.spi.controller;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
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
}
