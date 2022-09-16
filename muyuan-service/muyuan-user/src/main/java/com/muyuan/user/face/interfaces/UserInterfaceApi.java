package com.muyuan.user.face.interfaces;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.user.api.UserInterface;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.api.dto.UserQueryRequest;
import com.muyuan.user.domain.model.entity.user.User;
import com.muyuan.user.domain.service.UserDomainService;
import com.muyuan.user.face.dto.mapper.UserMapper;
import com.muyuan.user.face.dto.mapper.UserMapperImpl;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Optional;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = UserInterface.class
)
public class UserInterfaceApi implements UserInterface {

    private static final UserMapper USER_MAPPER = new UserMapperImpl();

    private UserDomainService userDomainService;

    @Override
    public Result<UserDTO> getUserByUsername(UserQueryRequest request) {
        Optional<User> operator = userDomainService.getUserByUsername(
                USER_MAPPER.toCommand(request)
        );

        return operator.map(USER_MAPPER::toDto)
                .map(ResultUtil::success)
                .orElse(ResultUtil.fail(ResponseCode.USER_ONT_FOUND));
    }

}
