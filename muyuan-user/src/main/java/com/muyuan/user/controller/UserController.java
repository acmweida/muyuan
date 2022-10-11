package com.muyuan.user.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.dto.UserVO;
import com.muyuan.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @ClassName UserController
 * Description 用户Controller
 * @Author 2456910384
 * @Date 2022/10/11 14:23
 * @Version 1.0
 */
@RestController()
@Api(tags = {"用户接口"})
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/user/get")
    @ApiOperation(value = "获取指定用户信息")
    public Result<UserVO> getUserInfo() {
        final Optional<UserVO> userInfo = userService.get();
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }


}
