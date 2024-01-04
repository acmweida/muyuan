package com.muyuan.manager.goods.facade.controller;

import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.goods.api.dto.AttributeDTO;
import com.muyuan.manager.goods.dto.AttributeParams;
import com.muyuan.manager.goods.dto.converter.AttributeConverter;
import com.muyuan.manager.goods.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 商品分类属性Controller
 *
 * @author ${author}
 * @date 2022-06-23T15:09:05.397+08:00
 */
@RestController
@RequestMapping("/category/attribute")
@AllArgsConstructor
public class AttributeController {

    private AttributeService attributeService;

    private AttributeConverter converter;


    /**
     * 获取品牌详细信息
     */
    @RequirePermissions("category:attribute:query")
    @GetMapping(value = "/{id}")
    public Result<AttributeDTO> get(@PathVariable("id") Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<AttributeDTO> hander = attributeService.get(id);
        return hander.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    /**
     * 新增商品分类属性
     */
    @RequirePermissions("category:attribute:add")
    @Log(title = "商品分类属性", businessType = BusinessType.INSERT)
    @Operation(summary = "商品分类属性新增")
    //    //    @OperationSupport(ignoreParameters = "id")
    @PostMapping
    public Result add(@RequestBody @Validated(AttributeParams.Add.class) AttributeParams params) {
        return ResultUtil.success(attributeService.add(params));
    }

    /**
     * 修改商品分类属性
     */
    @RequirePermissions("category:attribute:edit")
    @Log(title = "商品分类属性", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "商品分类属性更新")
    public Result update(@RequestBody @Validated(AttributeParams.Update.class) AttributeParams params) {
        return ResultUtil.success(attributeService.update(converter.to(params)));
    }

    /**
     * 修改商品分类属性
     */
    @RequirePermissions("category:attribute:edit")
    @Log(title = "商品分类属性", businessType = BusinessType.UPDATE)
    @PutMapping("values")
    @Operation(summary = "商品分类属性可选值更新")
    //    @OperationSupport(includeParameters = {"id","values"})
    public Result updateValues(@RequestBody @Validated(AttributeParams.ValuesUpdate.class) AttributeParams params) {
        return ResultUtil.success(attributeService.updateValues(params));
    }

    /**
     * 删除商品分类属性
     */
    @Operation(summary = "商品分类属性删除")
    @Parameters(
            {@Parameter(name = "id", description = "主键", in = ParameterIn.PATH)}
    )
    @RequirePermissions("goods:category:attribute:remove")
    @Log(title = "商品分类属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        return ResultUtil.success(attributeService.delete(ids));
    }
}
