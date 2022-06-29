package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.product.domains.model.CategoryAttribute;
import com.muyuan.product.domains.service.CategoryAttributeDomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 查询商品分类属性列表
     */
    @RequirePermissions("product:category:attribute:query")
    @GetMapping("/list")
    public Result list(CategoryAttribute categoryAttribute)
    {
        List<CategoryAttribute> list = categoryAttributeDomainService.selectCategoryAttributeList(categoryAttribute);
        return ResultUtil.success(list);
    }



    /**
     * 获取商品分类属性详细信息
     */
    @RequirePermissions("product:category:attribute:query")
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") Long id)
    {
        return ResultUtil.success(categoryAttributeDomainService.selectCategoryAttributeById(id));
    }

    /**
     * 新增商品分类属性
     */
    @RequirePermissions("product:category:attribute:add")
    @Log(title = "商品分类属性", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody CategoryAttribute categoryAttribute)
    {
        return ResultUtil.success(categoryAttributeDomainService.insertCategoryAttribute(categoryAttribute));
    }

    /**
     * 修改商品分类属性
     */
    @RequirePermissions("product:category:attribute:edit")
    @Log(title = "商品分类属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody CategoryAttribute categoryAttribute)
    {
        return ResultUtil.success(categoryAttributeDomainService.updateCategoryAttribute(categoryAttribute));
    }

    /**
     * 删除商品分类属性
     */
    @RequirePermissions("product:category:attribute:remove")
    @Log(title = "商品分类属性", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids)
    {
        return ResultUtil.success(categoryAttributeDomainService.deleteCategoryAttributeByIds(ids));
    }
}
