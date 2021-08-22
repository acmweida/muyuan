package com.muyuan.member.spi.controller;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.dto.AccountLoginDTO;
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
    private UserService userService;

    public Result register(RegisterDTO register) {
        if (userService.register(register) == 0) {
          return   ResultUtil.render("注册成功");
        }
        return ResultUtil.renderFail("注册失败");
    }


    public Result login(AccountLoginDTO loginInfo) {
        Optional<UserVO> login = userService.login(loginInfo);
        if (login.isPresent()) {
            ResultUtil.render("登录成功",login.get());
        }

        return ResultUtil.renderFail("登录失败");
    }
}
