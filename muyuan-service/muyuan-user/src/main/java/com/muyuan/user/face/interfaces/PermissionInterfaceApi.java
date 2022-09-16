package com.muyuan.user.face.interfaces;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.domain.service.UserDomainService;
import com.muyuan.user.face.dto.mapper.PermissionMapper;
import com.muyuan.user.face.dto.mapper.PermissionMapperImpl;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Set;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  权限
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = PermissionInterface.class
)
public class PermissionInterfaceApi implements PermissionInterface {

    private static final PermissionMapper PERMISSION_MAPPER = new PermissionMapperImpl();

    private UserDomainService userDomainService;

    @Override
    public Set<String> getPermissionByRoleCodes(PermissionQueryRequest request) {
        return null;
    }
}
