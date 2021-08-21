package com.muyuan.member.controller;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.member.dto.AccountLoginDTO;
import com.muyuan.member.dto.RegisterDTO;
import com.muyuan.member.service.UserService;
import com.muyuan.member.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
@Api(tags = {"用户接口"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(RegisterDTO register) {
        return ResultUtil.render();
    }

    @ApiOperation(value = "账号登录接口",code = 0)
    @PostMapping("/login")
    public Result login(@RequestBody  AccountLoginDTO loginInfo) {
        Optional<UserVO> login = userService.login(loginInfo);
        if (login.isPresent()) {
            ResultUtil.render("登录成功",login.get());
        }

        return ResultUtil.renderFail("登录失败");
    }
}
