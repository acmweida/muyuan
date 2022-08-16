package com.muyuan.manager.product.interfaces.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.manager.product.domains.model.Feature;
import com.muyuan.manager.product.domains.service.FeatureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通用特征量Controller
 * 
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@RestController
@RequestMapping("/feature")
@AllArgsConstructor
public class FeatureController {

    private FeatureService featureService;

    /**
     * 查询通用特征量列表
     */
    @RequirePermissions("product:feature:list")
    @GetMapping("/list")
    public Result list(@ModelAttribute Feature feature)
    {
        List<Feature> list = featureService.selectFeatureList(feature);
        return ResultUtil.success(list);
    }



    /**
     * 获取通用特征量详细信息
     */
    @RequirePermissions("product:feature:query")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable("id") Long id)
    {
        return ResultUtil.success(featureService.selectFeatureById(id));
    }

    /**
     * 新增通用特征量
     */
    @RequirePermissions("product:feature:add")
    @Log(title = "通用特征量", businessType = BusinessType.INSERT)
    @PostMapping
    public Result add(@RequestBody Feature feature)
    {
        return ResultUtil.success(featureService.insertFeature(feature));
    }

    /**
     * 修改通用特征量
     */
    @RequirePermissions("product:feature:edit")
    @Log(title = "通用特征量", businessType = BusinessType.UPDATE)
    @PutMapping
    public Result edit(@RequestBody Feature feature)
    {
        return ResultUtil.success(featureService.updateFeature(feature));
    }

    /**
     * 删除通用特征量
     */
    @RequirePermissions("product:feature:remove")
    @Log(title = "通用特征量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long[] ids)
    {
        return ResultUtil.success(featureService.deleteFeatureByIds(ids));
    }
}
