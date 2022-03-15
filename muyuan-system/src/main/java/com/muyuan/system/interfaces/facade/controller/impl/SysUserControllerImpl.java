package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.JwtUtils;
import com.muyuan.system.domain.model.SysUser;
import com.muyuan.system.domain.query.SysMenuQuery;
import com.muyuan.system.domain.query.SysRoleQuery;
import com.muyuan.system.domain.query.SysUserQuery;
import com.muyuan.system.domain.vo.SysUserVO;
import com.muyuan.system.interfaces.assembler.SysUserInfoAssembler;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import com.muyuan.system.interfaces.facade.controller.SysUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class SysUserControllerImpl implements SysUserController {

    @Autowired
    SysUserQuery sysUserQuery;

    @Autowired
    SysRoleQuery sysRoleQuery;

    @Autowired
    SysMenuQuery sysMenuQuery;

    @Override
    public Result<SysUserVO> getUserInfo() {
        Long userId = JwtUtils.getUserId();
        final Optional<SysUser> userInfo = sysUserQuery.getUserInfo(userId);
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        List<String> roleNames = JwtUtils.getRoles();

        Set<String> perms = getMenuPermissionByRoleNames(roleNames);
        SysUserVO sysUserVO = SysUserInfoAssembler.buildUserVO(userInfo.get(),roleNames,perms);
        return ResultUtil.success(sysUserVO);
    }



    private Set<String> getMenuPermissionByRoleNames(List<String> roleIds) {
        return sysMenuQuery.selectMenuPermissionByRoleNames(roleIds);
    }

    public Result accountRegister(RegisterDTO register) {
        int registerResult = sysUserQuery.accountRegister(register);
        if (registerResult == 0) {
            return   ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }
}
