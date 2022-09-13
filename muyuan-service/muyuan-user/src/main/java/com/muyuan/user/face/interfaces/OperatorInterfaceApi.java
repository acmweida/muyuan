package com.muyuan.user.face.interfaces;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.user.api.OperatorInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.domain.service.UserDomainService;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.Assert;

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
@DubboService(group = ServiceTypeConst.STORE_SYSTEM, version = "1.0"
        , interfaceClass = OperatorInterface.class
)
public class OperatorInterfaceApi implements OperatorInterface {


    private UserDomainService userDomainService;

    @Override
    public Result<OperatorDTO> getUserByUsername(String username) {
        OperatorDTO userByUsername = userDomainService.getUserByUsername(username);
        if (null == userByUsername) {
            return ResultUtil.fail("用户信息不存在");
        }

        return ResultUtil.success(userByUsername);
    }

    @Override
    public Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes) {
        return userDomainService.getMenuPermissionByRoleCodes(roleCodes);
    }

    @Override
    public void linkShop(Long shopId) {
        Assert.notNull(shopId,"shopId is null");
        userDomainService.linkShop(shopId);
    }

}
