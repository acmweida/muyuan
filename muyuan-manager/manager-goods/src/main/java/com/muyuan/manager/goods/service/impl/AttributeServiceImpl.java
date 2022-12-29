package com.muyuan.manager.goods.service.impl;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.goods.api.AttributeInterface;
import com.muyuan.goods.api.dto.AttributeDTO;
import com.muyuan.goods.api.dto.AttributeRequest;
import com.muyuan.goods.api.dto.AttributeValueUpdateRequest;
import com.muyuan.manager.goods.dto.AttributeParams;
import com.muyuan.manager.goods.service.AttributeService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;


/**
 * 商品分类属性Service业务层处理
 * 
 * @author ${author}
 * @date 2022-06-23T14:17:01.512+08:00
 */
@Service
public class AttributeServiceImpl implements AttributeService
{

    @DubboReference(group = ServiceTypeConst.GOODS, version = "1.0")
    private AttributeInterface attributeInterface;

    @Override
    public Optional<AttributeDTO> get(Long id) {
        return Optional.of(id)
                .map(id_ -> {
                    Result<AttributeDTO> permissioHander = attributeInterface.getAttribute(id_);
                    return ResultUtil.getOr(permissioHander, null);
                });
    }

    /**
     * 新增商品分类属性
     * 
     * @param params 商品分类属性
     * @return 结果
     */
    @Override
    public Result add(AttributeParams params)
    {
        AttributeRequest request = AttributeRequest.builder()
                .categoryCode(params.getCategoryCode())
                .code(params.getCode())
                .name(params.getName())
                .inputType(params.getInputType())
                .type(params.getType())
                .valueType(params.getValueType())
                .valueReference(params.getValueReference())
                .values(params.getValues())
                .opt(SecurityUtils.getOpt())
                .build();

        return attributeInterface.add(request);
    }

    /**
     * 修改商品分类属性
     * 
     * @param request 商品分类属性
     * @return 结果
     */
    @Override
    public Result update(AttributeRequest request)
    {
        request.setOpt(SecurityUtils.getOpt());
        return attributeInterface.updateAttribute(request);
    }

    @Override
    public Result updateValues(AttributeParams params) {
        return attributeInterface.updateValues(AttributeValueUpdateRequest.builder()
                .id(params.getId())
                .values(params.getValues())
                .opt(SecurityUtils.getOpt())
                .build());
    }

    /**
     * 批量删除商品分类属性
     * 
     * @param ids 需要删除的商品分类属性主键
     * @return 结果
     */
    @Override
    public Result delete(Long[] ids)
    {
        if (ObjectUtils.isEmpty(ids)) {
            return ResultUtil.fail();
        }
        return attributeInterface.deleteAttribute(ids);
    }

}
