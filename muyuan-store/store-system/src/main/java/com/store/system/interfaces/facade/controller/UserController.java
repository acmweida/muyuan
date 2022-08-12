package com.store.system.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.store.system.application.service.UserApplicationService;
import com.store.system.domains.dto.RegisterDTO;
import com.store.system.domains.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@Api(tags = {"商家账号接口"})
@AllArgsConstructor
public class UserController {

    private UserApplicationService userApplicationService;

    private RedisCacheService redisCacheService;

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

        if (!register.getCode().equals(redisCacheService.get(GlobalConst.CAPTCHA_KEY_PREFIX+register.getUuid()))) {
            return ResultUtil.fail("验证码过期或错误");
        }

        userApplicationService.add(register);
        return ResultUtil.success("注册成功");

    }
}
