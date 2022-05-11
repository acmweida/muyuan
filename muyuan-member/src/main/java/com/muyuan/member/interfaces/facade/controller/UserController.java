package com.muyuan.member.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.member.application.service.UserApplicationService;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.service.UserDomainService;
import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@Api(tags = {"商家账号接口"})
@AllArgsConstructor
public class UserController {

    private UserApplicationService userApplicationService;

    private UserDomainService userDomainService;

    @GetMapping("/user")
    @ApiOperation(value = "获取用户信息")
    public Result<UserVO> getUserInfo() {
        final Optional<UserVO> userInfo = userApplicationService.getUserInfo();
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }

    @ApiOperation(value = "账号密码注册", code = 0)
    @PostMapping("/user")
    public Result add(@RequestBody @Validated RegisterDTO register) {
        if (GlobalConst.NOT_UNIQUE.equals(userDomainService.checkAccountNameUnique(new User(register.getUsername())))) {
            return ResultUtil.fail("账号已存在");
        }

        userDomainService.add(register);
        return ResultUtil.success("注册成功");

    }
}
