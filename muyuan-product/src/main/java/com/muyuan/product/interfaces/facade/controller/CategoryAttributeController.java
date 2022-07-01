package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.product.domains.dto.CategoryAttributeDTO;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.service.CategoryAttributeDomainService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品分类属性Controller
 *
 * @author ${author}
 * @date 2022-06-23T15:09:05.397+08:00
 */
@RestController
@RequestMapping("/category/attribute")
@AllArgsConstructor
public class CategoryAttributeController {

    private CategoryAttributeDomainService categoryAttributeDomainService;


    /**
     * 新增商品分类属性
     */
    @RequirePermissions("product:category:attribute:add")
    @Log(title = "商品分类属性", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody @Validated CategoryAttributeDTO categoryAttribute)
    {
        categoryAttributeDomainService.add(categoryAttribute);
        return ResultUtil.success();
    }

    /**
     * 修改商品分类属性
     */
    @RequirePermissions("product:category:attribute:edit")
    @Log(title = "商品分类属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result update(@RequestBody CategoryAttribute categoryAttribute)
    {
        categoryAttributeDomainService.update(categoryAttribute);
        return ResultUtil.success();
    }

    /**
     * 删除商品分类属性
     */
    @RequirePermissions("product:category:attribute:remove")
    @Log(title = "商品分类属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable String[] ids)
    {
        categoryAttributeDomainService.delete(ids);
        return ResultUtil.success();
    }
}
