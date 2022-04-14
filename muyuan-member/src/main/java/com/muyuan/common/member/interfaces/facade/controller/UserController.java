package com.muyuan.common.member.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.common.member.application.query.MenuQuery;
import com.muyuan.common.member.application.query.RoleQuery;
import com.muyuan.common.member.application.query.UserQuery;
import com.muyuan.common.member.domain.model.User;
import com.muyuan.common.member.domain.vo.UserVO;
import com.muyuan.common.member.interfaces.assembler.UserInfoAssembler;
import com.muyuan.common.member.interfaces.dto.RegisterDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class UserController {


    @Autowired
    UserQuery userQuery;

    @Autowired
    RoleQuery roleQuery;

    @Autowired
    MenuQuery menuQuery;

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public Result<UserVO> getUserInfo() {
        Long userId = SecurityUtils.getUserId();
        final Optional<User> userInfo = userQuery.getUserInfo(userId);
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        List<String> roleNames = SecurityUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleNames(roleNames);
        UserVO userVO = UserInfoAssembler.buildUserVO(userInfo.get(),roleNames,perms);
        return ResultUtil.success(userVO);
    }



    private Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return menuQuery.selectMenuPermissionByRoleNames(roleIds);
    }


    @ApiOperation(value = "账号密码注册",code = 0)
    @PostMapping("/accountRegister")
    public Result accountRegister(RegisterDTO register) {
        int registerResult = userQuery.accountRegister(register);
        if (registerResult == 0) {
            return   ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }
}
