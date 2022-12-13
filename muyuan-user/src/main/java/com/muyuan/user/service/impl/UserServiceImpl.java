package com.muyuan.user.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.user.api.PermissionInterface;
import com.muyuan.user.api.UserInterface;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.dto.UserVO;
import com.muyuan.user.dto.converter.UserConverter;
import com.muyuan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
public class UserServiceImpl implements UserService {

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private PermissionInterface permissionInterface;

    @DubboReference(group = ServiceTypeConst.USER, version = "1.0")
    private UserInterface userInterface;

    @Autowired
    private UserConverter converter;

    @Override
    public Optional<UserVO> get() {
        Long userId = SecurityUtils.getUserId();

        String username = SecurityUtils.getUsername();
        PlatformType platformType = SecurityUtils.getPlatformType();

        final Result<UserDTO> userInfo = userInterface.getUserByUsername(username,platformType);
        if (!ResultUtil.isSuccess(userInfo)) {
            log.info("userId :{} 未找到", userId);
            return Optional.empty();
        }

        UserVO userVO = converter.toVO(userInfo.getData());

        Result<Set<String>> permissions = permissionInterface.getPermissionByUserID(userId,platformType);
        userVO.setPermissions(ResultUtil.getOr(permissions, HashSet::new));

        return Optional.of(userVO);
    }
}
