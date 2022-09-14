package com.muyuan.user.face.interfaces;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.user.api.OperatorInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorQueryRequest;
import com.muyuan.user.domain.model.entity.user.Operator;
import com.muyuan.user.domain.service.OperatorDomainService;
import com.muyuan.user.face.dto.mapper.OperatorMapper;
import com.muyuan.user.face.dto.mapper.OperatorMapperImpl;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  用户
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = OperatorInterface.class
)
public class OperatorInterfaceApi implements OperatorInterface {

    private static final OperatorMapper operatorMapper = new OperatorMapperImpl();

    private OperatorDomainService operatorDomainService;

    @Override
    public Result<OperatorDTO> getOperatorByUsername(OperatorQueryRequest request) {
        Optional<Operator> operator = operatorDomainService.getOperatorByUsername(
                operatorMapper.toCommand(request)
        );

        return operator.map(operatorMapper::toDto)
                .map(ResultUtil::success)
                .orElse(ResultUtil.fail(ResponseCode.USER_ONT_FOUND));
    }

    @Override
    public Set<String> getMenuPermissionByRoleCodes(List<String> roleCodes) {
//        return operatorDomainService.getMenuPermissionByRoleCodes(roleCodes);
        return null;
    }

    @Override
    public void linkShop(Long shopId) {
        Assert.notNull(shopId, "shopId is null");
//        operatorDomainService.linkShop(shopId);
    }

}
