package com.muyuan.store.system.interfaces.facade.api;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.store.system.application.service.UserApplicationService;
import com.muyuan.store.system.domains.service.UserDomainService;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.UserQueryRequest;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
public class UserInterfaceApi  {

    private UserApplicationService sysUserApplicationService;

    private UserDomainService userDomainService;

    public Result<OperatorDTO> getUserByUsername(UserQueryRequest request) {
        OperatorDTO userByUsername = sysUserApplicationService.getUserByUsername(request.getUsername());
        if (null == userByUsername) {
            return ResultUtil.fail("用户信息不存在");
        }

        return ResultUtil.success(userByUsername);
    }
}
