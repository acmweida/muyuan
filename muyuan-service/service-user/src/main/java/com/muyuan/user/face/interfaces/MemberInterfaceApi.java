package com.muyuan.user.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.PlatformType;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.validator.ValidatorHolder;
import com.muyuan.user.api.MemberInterface;
import com.muyuan.user.api.dto.OperatorDTO;
import com.muyuan.user.api.dto.OperatorRequest;
import com.muyuan.user.api.dto.UserQueryRequest;
import com.muyuan.user.domain.model.entity.Operator;
import com.muyuan.user.domain.model.valueobject.RoleID;
import com.muyuan.user.domain.model.valueobject.UserID;
import com.muyuan.user.domain.model.valueobject.Username;
import com.muyuan.user.domain.service.OperatorService;
import com.muyuan.user.face.dto.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @ClassName UserInterfaceApi
 * Description 内部接口  会员
 * @Author 2456910384
 * @Date 2022/3/2 17:12
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.USER, version = "1.0"
        , interfaceClass = MemberInterface.class,
        methods = {
                @Method(name = "add", retries = 0)
        }
)
public class MemberInterfaceApi implements MemberInterface {

    private UserMapper USER_MAPPER;

    private OperatorService operatorService;

    @Override
    public Result<Page<OperatorDTO>> list(UserQueryRequest request) {
        Page<Operator> list = operatorService.list(USER_MAPPER.toCommand(request));

        return ResultUtil.success(Page.copy(list, USER_MAPPER.toDto(list.getRows())));
    }

    @Override
    public Result<OperatorDTO> get(Long id) {
        Optional<Operator> handler = operatorService.getOperatorByyId(new UserID(id), PlatformType.OPERATOR);

        return handler.map(USER_MAPPER::toDto)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result add(OperatorRequest request) {
        ValidatorHolder.validate(request, OperatorRequest.Add.class);

        if (GlobalConst.NOT_UNIQUE.equals(operatorService.checkUnique(new Operator.Identify(new Username(request.getUsername()))))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = operatorService.addOperator(USER_MAPPER.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<Page<OperatorDTO>> selectAllocatedList(UserQueryRequest request) {
        ValidatorHolder.validate(request);
        Page<Operator> list = operatorService.selectAllocatedList(USER_MAPPER.toCommand(request));

        return ResultUtil.success(Page.copy(list, USER_MAPPER.toDto(list.getRows())));
    }

    @Override
    public Result<Page<OperatorDTO>> selectUnallocatedList(UserQueryRequest request) {
         ValidatorHolder.validate(request);

        Page<Operator> list = operatorService.selectUnallocatedList(USER_MAPPER.toCommand(request));

        return ResultUtil.success(Page.copy(list, USER_MAPPER.toDto(list.getRows())));
    }

    @Override
    public Result authRole(Long userId, Long[] roleIds) {
        UserID userID = new UserID(userId);
        List<RoleID> roleIDs = new ArrayList<>();
        for (Long roleId : roleIds) {
            roleIDs.add(new RoleID(roleId));
        }
        if (operatorService.authRole(userID, roleIDs)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

}
