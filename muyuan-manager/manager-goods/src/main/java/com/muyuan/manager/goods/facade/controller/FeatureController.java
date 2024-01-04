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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 通用特征量Controller
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@RestController
@RequestMapping("/feature")
@AllArgsConstructor
@Tag(name = "商品特征量接口")
public class FeatureController {

    private FeatureService featureService;

    private FeatureConverter converter;

    /**
     * 查询通用特征量列表
     *
     * @return
     */
    @Operation(summary = "特征量列表查询")
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
    @Operation(summary = "特征量选项")
    @Parameters({
            @Parameter(name = "name", description = "特征量名称", in = ParameterIn.QUERY),
    })
    public Result options(@RequestParam @Validated @NotBlank(message = "特征量名称必填") String name) {
        return ResultUtil.success(featureService.options(
                FeatureQueryParams.builder().name(name).build()
        ));
    }
}
