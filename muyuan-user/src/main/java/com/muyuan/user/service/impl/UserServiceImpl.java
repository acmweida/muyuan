package com.muyuan.user.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.UserInterface;
import com.muyuan.user.api.dto.PermissionQueryRequest;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.api.dto.UserQueryRequest;
import com.muyuan.user.dto.UserVO;
import com.muyuan.user.dto.converter.UserConverter;
import com.muyuan.user.dto.converter.UserConverterImpl;
import com.muyuan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @ClassName UserServiceImpl
 * Description User Service Impl
 * @Author 2456910384
 * @Date 2022/10/11 14:32
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private PermissionInterface permissionInterface;

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private UserInterface userInterface;

    private static final UserConverter converter = new UserConverterImpl();

    @Override
    public Optional<UserVO> get() {
        Long userId = SecurityUtils.getUserId();

        String username = SecurityUtils.getUsername();
        PlatformType platformType = PlatformType.valueOf(SecurityUtils.getPlatformType());

        final Result<UserDTO> userInfo = userInterface.getUserByUsername(UserQueryRequest.builder()
                .username(username)
                .platformType(platformType)
                .build());
        if (!ResultUtil.isSuccess(userInfo)) {
            log.info("userId :{} 未找到", userId);
            return Optional.empty();
        }
        Set<String> permissions = permissionInterface.getPermissionByUserID(PermissionQueryRequest.builder()
                .userId(userId)
                .platformType(platformType)
                .build());
        UserVO userVO = converter.toVO(userInfo.getData());
        userVO.setPermissions(permissions);
        return Optional.of(userVO);
    }
}
