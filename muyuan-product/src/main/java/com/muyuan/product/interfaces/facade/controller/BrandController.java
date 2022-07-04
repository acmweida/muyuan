package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.product.domains.model.Brand;
import com.muyuan.product.domains.service.BrandDomainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌Controller
 * 
 * @author ${author}
 * @date 2022-07-04T16:07:13.074+08:00
 */
@RestController
@RequestMapping("/brand")
@AllArgsConstructor
public class BrandController {

    private BrandDomainService brandDomainService;

    /**
     * 查询品牌列表
     */
    @RequirePermissions("product:brand:list")
    @GetMapping("/list")
    public Result list(Brand brand)
    {
        List<Brand> list = brandDomainService.selectBrandList(brand);
        return ResultUtil.success(list);
    }



    /**
     * 获取品牌详细信息
     */
    @RequirePermissions("product:brand:query")
    @GetMapping(value = "/{id}")
    public Result getInfo(@PathVariable("id") Long id)
    {
        return ResultUtil.success(brandDomainService.selectBrandById(id));
    }

    /**
     * 新增品牌
     */
    @RequirePermissions("product:brand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody Brand brand)
    {
        return ResultUtil.success(brandDomainService.insertBrand(brand));
    }

    /**
     * 修改品牌
     */
    @RequirePermissions("product:brand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody Brand brand)
    {
        return ResultUtil.success(brandDomainService.updateBrand(brand));
    }

    /**
     * 删除品牌
     */
    @RequirePermissions("product:brand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids)
    {
        return ResultUtil.success(brandDomainService.deleteBrandByIds(ids));
    }
}
