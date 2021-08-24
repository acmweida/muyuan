package com.muyuan.member.spi.controller;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.service.UserService;
import com.muyuan.member.spi.LoginController;
import com.muyuan.member.vo.AccountLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginControllerImpl implements LoginController {

    @Autowired
    private UserService userService;

    public Result accountRegister(RegisterDTO register) {
        int registerResult = userService.register(register);
        if (registerResult == 0) {
          return   ResultUtil.render("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.renderFail("账号已存在");
        }
        return ResultUtil.renderFail("注册失败");
    }


    public Result accountLogin(AccountLoginDTO loginInfo) {
        Optional<AccountLoginVo> login = userService.login(loginInfo);
        if (login.isPresent()) {
            ResultUtil.render("登录成功",login.get());
        }

        return ResultUtil.renderFail("登录失败");
    }
}
