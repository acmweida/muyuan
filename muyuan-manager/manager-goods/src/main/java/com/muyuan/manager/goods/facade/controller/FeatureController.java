package com.muyuan.manager.goods.facade.controller;


import com.muyuan.common.bean.Result;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.goods.dto.FeatureParams;
import com.muyuan.manager.goods.dto.FeatureQueryParams;
import com.muyuan.manager.goods.dto.converter.FeatureConverter;
import com.muyuan.manager.goods.service.FeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;

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

    private FeatureConverter converter;

    /**
     * 查询通用特征量列表
     *
     * @return
     */
    @ApiOperation(value = "特征量列表查询")
    @RequirePermissions("goods:feature:query")
    @GetMapping("/list")
    public Result list(@ModelAttribute FeatureQueryParams params) {
        return ResultUtil.success(featureService.list(params));
    }


    /**
     * 获取通用特征量详细信息
     */
    @RequirePermissions("goods:feature:query")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable("id") Long id) {
        return ResultUtil.success(featureService.get(id));
    }

    /**
     * 新增通用特征量
     */
    @RequirePermissions("goods:feature:add")
    @Log(title = "通用特征量", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody @Validated(FeatureParams.Add.class) FeatureParams params) {
        return featureService.add(converter.to(params));
    }

    /**
     * 修改通用特征量
     */
    @RequirePermissions("goods:feature:edit")
    @Log(title = "通用特征量", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody @Validated(FeatureParams.Update.class) FeatureParams params) {
        featureService.update(converter.to(params));
        return ResultUtil.success();
    }

    /**
     * 删除通用特征量
     */
    @RequirePermissions("goods:feature:remove")
    @Log(title = "通用特征量", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids) {
        return featureService.deleteById(ids);
    }

    /**
     * 查询品牌
     */
    @RequirePermissions("goods:feature:query")
    @GetMapping("/options")
    @ApiOperation("特征量选项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "特征量名称", dataTypeClass = String.class, paramType = "query", required = true),
    })
    public Result options(@RequestParam @Validated @NotBlank(message = "特征量名称必填") String name) {
        return ResultUtil.success(featureService.options(
                FeatureQueryParams.builder().name(name).build()
        ));
    }
}
