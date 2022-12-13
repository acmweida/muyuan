package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.user.api.UserInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.UserDTO;
import com.muyuan.user.domain.model.entity.Merchant;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.service.MerchantService;
import com.muyuan.user.domain.service.OperatorService;
import com.muyuan.user.face.dto.mapper.UserMapper;
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

    private UserMapper USER_MAPPER;

    private OperatorService operatorService;

    private MerchantService merchantService;

    @Override
    public Result<UserDTO> getUserByUsername(String username,PlatformType platformType) {
        switch (platformType) {
            case OPERATOR:
                Optional<Operator> operator = operatorService.getUserByUsername(username);
                return operator.map(USER_MAPPER::toUser)
                        .map(ResultUtil::<UserDTO>success)
                        .orElse(ResultUtil.<UserDTO>fail(ResponseCode.USER_ONT_FOUND));
            case MERCHANT:
                Optional<Merchant> merchant = merchantService.getUserByUsername(username);
                return merchant.map(USER_MAPPER::toUser)
                        .map(ResultUtil::<UserDTO>success)
                        .orElse(ResultUtil.<UserDTO>fail(ResponseCode.USER_ONT_FOUND));
        }

        return ResultUtil.<OperatorDTO>fail(ResponseCode.USER_ONT_FOUND);
    }

}
