package com.muyuan.member.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.member.application.service.UserApplicationService;
import com.muyuan.member.domain.query.MenuQuery;
import com.muyuan.member.domain.query.RoleQuery;
import com.muyuan.member.domain.query.UserQuery;
import com.muyuan.member.domain.model.User;
import com.muyuan.member.domain.service.UserDomainService;
import com.muyuan.member.domain.vo.UserVO;
import com.muyuan.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.member.interfaces.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController()
@RequestMapping(value = "/user")
@Api(tags = {"用户接口"})
@AllArgsConstructor
public class UserController {

    private UserApplicationService userApplicationService;

    private UserDomainService userDomainService;

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public Result<UserVO> getUserInfo() {
        final Optional<UserVO> userInfo = userApplicationService.getUserInfo();
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }



    @ApiOperation(value = "账号密码注册",code = 0)
    @PostMapping("/accountRegister")
    public Result accountRegister(RegisterDTO register) {
        int registerResult = userDomainService.add(register);
        if (registerResult == 0) {
            return   ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }
}
