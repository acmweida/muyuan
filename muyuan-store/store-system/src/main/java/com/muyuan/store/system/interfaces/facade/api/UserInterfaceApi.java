package com.muyuan.store.system.interfaces.facade.api;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.store.system.application.service.UserApplicationService;
import com.muyuan.store.system.domains.service.UserDomainService;
import com.muyuan.user.api.UserInterface;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.api.dto.UserQueryRequest;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.STORE_SYSTEM, version = "1.0"
        , interfaceClass = UserInterface.class
)
public class UserInterfaceApi implements UserInterface {

    private UserApplicationService sysUserApplicationService;

    private UserDomainService userDomainService;

//    @Override
//    public Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes) {
//        return sysUserApplicationService.getMenuPermissionByRoleCodes(roleCodes);
//    }
//
//    @Override
//    public void linkShop(Long shopId) {
//        Assert.notNull(shopId,"shopId is null");
//        userDomainService.linkShop(shopId);
//    }

    @Override
    public Result<UserDTO> getUserByUsername(UserQueryRequest request) {
        UserDTO userByUsername = sysUserApplicationService.getUserByUsername(request.getUsername());
        if (null == userByUsername) {
            return ResultUtil.fail("用户信息不存在");
        }

        return ResultUtil.success(userByUsername);
    }
}
