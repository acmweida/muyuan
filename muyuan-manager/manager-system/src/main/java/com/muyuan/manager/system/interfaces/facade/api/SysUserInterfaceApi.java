package com.muyuan.manager.system.interfaces.facade.api;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.manager.system.application.service.SysUserApplicationService;
import com.muyuan.menager.api.SysUserInterface;
import com.muyuan.menager.interfaces.to.SysUserTO;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Set;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.MANAGER_SYSTEM,version = "1.0",interfaceClass = SysUserInterface.class)
public class SysUserInterfaceApi implements SysUserInterface {

    private SysUserApplicationService sysUserApplicationService;

    @Override
    public Result<SysUserTO> getUserByUsername(String username) {
        SysUserTO sysUserTO = sysUserApplicationService.getUserByUsername(username);
        if (null == sysUserTO) {
            return ResultUtil.fail("用户信息不存在");
        }

        return ResultUtil.success(sysUserTO);
    }

    @Override
    public Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes) {
        return sysUserApplicationService.getMenuPermissionByRoleCodes(roleCodes);
    }

}
