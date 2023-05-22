package com.muyuan.goods.face.interfaces;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.core.validator.ValidatorHolder;
import com.muyuan.goods.api.AttributeInterface;
import com.muyuan.goods.api.dto.AttributeDTO;
import com.muyuan.goods.api.dto.AttributeQueryRequest;
import com.muyuan.goods.api.dto.AttributeRequest;
import com.muyuan.goods.api.dto.AttributeValueUpdateRequest;
import com.muyuan.goods.domains.model.entity.Attribute;
import com.muyuan.goods.application.AttributeService;
import com.muyuan.goods.face.dto.transfor.AttributeTransfer;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.Optional;

/**
 * @ClassName AttributeInterfaceApi
 * Description 内部接口  类目属性
 * @author ${author}
 * @date 2022-12-26T17:20:39.753+08:00
 * @Version 1.0
 */
@AllArgsConstructor
@DubboService(group = ServiceTypeConst.GOODS, version = "1.0"
        , interfaceClass = AttributeInterface.class,
        methods = {
                @Method(name = "add", retries = 0)
        }
)
public class AttributeInterfaceApi implements AttributeInterface {

    private AttributeTransfer transfer;

    private AttributeService attributeDomainService;

    @Override
    public Result<Page<AttributeDTO>> list(AttributeQueryRequest request) {
        Page<Attribute> list = attributeDomainService.list(transfer.toCommand(request));

        return ResultUtil.success( Page.copy(list,transfer.toDTO(list.getRows())));
    }

    @Override
    public Result add(AttributeRequest request) {
        if (attributeDomainService.exists(new Attribute.Identify(request.getCategoryCode(),request.getName()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }
        boolean flag = attributeDomainService.addAttribute(transfer.toCommand(request));
        return flag ? ResultUtil.success("添加成功") : ResultUtil.fail();
    }

    @Override
    public Result<AttributeDTO> getAttribute(Long id) {
        Optional<Attribute> handler = attributeDomainService.getAttribute(id);

        return handler.map(transfer::toDTO)
                .map(ResultUtil::success)
                .orElse(ResultUtil.error(ResponseCode.QUERY_NOT_EXIST));
    }

    @Override
    public Result updateAttribute(AttributeRequest request) {
        if (attributeDomainService.exists(new Attribute.Identify(request.getId()
                ,request.getCategoryCode(),request.getName()))) {
            return ResultUtil.fail(ResponseCode.UPDATE_EXIST);
        }

        boolean flag = attributeDomainService.updateAttribute(transfer.toCommand(request));
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result updateValues(AttributeValueUpdateRequest request) {
        ValidatorHolder.validate(request);

        boolean flag = attributeDomainService.updateValues(request);
        return flag ? ResultUtil.success("更新成功") : ResultUtil.fail();
    }

    @Override
    public Result deleteAttribute(Long... ids) {
        if (attributeDomainService.deleteAttributeById(ids)) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }
}
