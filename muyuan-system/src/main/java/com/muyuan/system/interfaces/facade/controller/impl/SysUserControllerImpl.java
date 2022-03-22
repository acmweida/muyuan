package com.muyuan.system.interfaces.facade.controller.impl;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.system.application.service.SysUserService;
import com.muyuan.system.application.vo.SysUserVO;
import com.muyuan.system.interfaces.dto.RegisterDTO;
import com.muyuan.system.interfaces.facade.controller.SysUserController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SysUserControllerImpl implements SysUserController {

    private SysUserService sysUserService;

    @Override
    public Result<SysUserVO> getUserInfo() {
        final Optional<SysUserVO> userInfo = sysUserService.getUserInfo();
        if (!userInfo.isPresent()) {
            return ResultUtil.fail("用户信息不存在");
        }
        return ResultUtil.success(userInfo.get());
    }

    public Result add(RegisterDTO register) {
        int registerResult = sysUserService.add(register);
        if (registerResult == 0) {
            return ResultUtil.success("注册成功");
        } else if (registerResult == 1) {
            return ResultUtil.fail("账号已存在");
        }
        return ResultUtil.fail("注册失败");
    }
}
