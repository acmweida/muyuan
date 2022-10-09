package com.muyuan.manager.product.interfaces.controller;


import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.product.domains.dto.FeatureDTO;
import com.muyuan.manager.product.domains.model.Feature;
import com.muyuan.manager.product.domains.service.FeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * 通用特征量Controller
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@RestController
@RequestMapping("/feature")
@AllArgsConstructor
@Api(tags = {"商品特征量接口"})
public class FeatureController {

    private FeatureService featureService;

    /**
     * 查询通用特征量列表
     * @return
     */
    @ApiOperation(value = "特征量列表查询")
    @RequirePermissions("product:feature:list")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "name", value = "特征量名称", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "code", value = "状态", dataTypeClass = String.class, paramType = "query"),
            @ApiImplicitParam(name = "status", value = "特征量编码", dataTypeClass = String[].class, paramType = "query")
    })
    public Result list(@ModelAttribute FeatureDTO featureDTO) {
        return ResultUtil.success(featureService.page(featureDTO));
    }


    /**
     * 获取通用特征量详细信息
     */
    @RequirePermissions("product:feature:query")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable("id") Long id) {
        return ResultUtil.success(featureService.get(id));
    }

    /**
     * 新增通用特征量
     */
    @RequirePermissions("product:feature:add")
    @Log(title = "通用特征量", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody Feature feature) {
        featureService.add(feature);
        return ResultUtil.success();
    }

    /**
     * 修改通用特征量
     */
    @RequirePermissions("product:feature:edit")
    @Log(title = "通用特征量", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody Feature feature) {
        featureService.update(feature);
        return ResultUtil.success();
    }

    /**
     * 删除通用特征量
     */
    @RequirePermissions("product:feature:remove")
    @Log(title = "通用特征量", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        featureService.delete(ids);
        return ResultUtil.success();
    }

    /**
     * 查询品牌
     */
    @RequirePermissions("product:feature:option")
    @GetMapping("/options")
    @ApiOperation("特征量选项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "特征量名称", dataTypeClass = String.class, paramType = "query",required = true),
    })
    public Result options(@RequestParam @Validated @NotBlank(message = "特征量名称必填") String name) {
        return ResultUtil.success(featureService.options(
                FeatureDTO.builder().name(name).build()
        ));
    }
}
