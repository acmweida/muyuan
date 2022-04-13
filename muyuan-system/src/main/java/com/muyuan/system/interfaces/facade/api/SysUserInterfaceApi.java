package com.muyuan.system.interfaces.facade.api;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.system.api.SysUserInterface;
import com.muyuan.system.application.service.SysUserService;
import com.muyuan.system.interfaces.dto.SysUserDTO;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.Service;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@Service(group = ServiceTypeConst.SYSTEM_SERVICE,version = "1.0")
@AllArgsConstructor
public class SysUserInterfaceApi implements SysUserInterface {

    private SysUserService sysUserService;

    @Override
    public Result<SysUserDTO> getUserByUsername(String username) {
        SysUserDTO sysUserDTO = sysUserService.getUserByUsername(username);
        if (null == sysUserDTO) {
            return ResultUtil.fail("用户信息不存在");
        }

        return ResultUtil.success(sysUserDTO);
    }


}
