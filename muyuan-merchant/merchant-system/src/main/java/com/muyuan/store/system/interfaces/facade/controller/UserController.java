package com.muyuan.store.system.interfaces.facade.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.redis.manage.RedisCacheService;
import com.muyuan.store.system.application.service.UserApplicationService;
import com.muyuan.store.system.domains.dto.RegisterDTO;
import com.muyuan.store.system.domains.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
@Tag(name = "商家账号接口")
@AllArgsConstructor
public class UserController {

    private UserApplicationService userApplicationService;

    private RedisCacheService redisCacheService;

    @GetMapping("/user")
    @Operation(summary = "获取用户信息")
    public Result<UserVO> getUserInfo() {
        final Optional<UserVO> userInfo = userApplicationService.getUserInfo();
        return userInfo.map(ResultUtil::success).orElseGet(() -> ResultUtil.fail("用户信息不存在"));
    }

    @Operation(summary = "账号密码注册")
    @PostMapping("/user")
    public Result add(@RequestBody @Validated RegisterDTO register) {

        if (!register.getCode().equals(redisCacheService.get(GlobalConst.CAPTCHA_KEY_PREFIX+register.getUuid()))) {
            return ResultUtil.fail("验证码过期或错误");
        }

        userApplicationService.add(register);
        return ResultUtil.success("注册成功");

    }
}
