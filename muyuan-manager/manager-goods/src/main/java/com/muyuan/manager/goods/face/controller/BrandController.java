package com.muyuan.manager.goods.face.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.goods.api.dto.BrandDTO;
import com.muyuan.manager.goods.dto.BrandParams;
import com.muyuan.manager.goods.dto.BrandQueryParams;
import com.muyuan.manager.goods.dto.converter.BrandConverter;
import com.muyuan.manager.goods.service.BrandService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    private BrandService brandService;

    private BrandConverter converter;
    /**
     * 查询品牌列表
     */
    @ApiOperation("品牌分页查询")
    @RequirePermissions("goods:brand:query")
    @GetMapping("/list")
    @ApiOperationSupport(ignoreParameters = {"categoryCodes","categoryCode"})
    public Result<Page<BrandDTO>> page(@ModelAttribute BrandQueryParams params) {
        Page<BrandDTO> list = brandService.list(params);
        return ResultUtil.success(list);
    }


    /**
     * 获取品牌详细信息
     */
    @RequirePermissions("goods:brand:query")
    @GetMapping(value = "/{id}")
    public Result<BrandDTO> get(@PathVariable("id") Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST);
        }

        Optional<BrandDTO> brand = brandService.get(id);
        return brand.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    /**
     * 新增品牌
     */
    @RequirePermissions("goods:brand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("品牌新增")
    @ApiOperationSupport(includeParameters = {"name","logo"})
    public Result add(@RequestBody @Validated(BrandParams.Add.class) BrandParams params) {
        return brandService.add(converter.to(params));
    }

    /**
     * 修改品牌
     */
    @RequirePermissions("goods:brand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("品牌信息修改")
    @ApiOperationSupport(ignoreParameters = {"auditStatus","status"})
    public Result edit(@RequestBody @Validated(BrandParams.Update.class) BrandParams params) {
        return brandService.update(converter.to(params));
    }

    /**
     * 修改品牌
     */
    @RequirePermissions("goods:brand:audit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping("/audit")
    @ApiOperation("品牌审核")
    @ApiOperationSupport(includeParameters = {"id","auditStatus"})
    public Result audit(@RequestBody @Validated(BrandParams.Audit.class) BrandParams params) {
        return  brandService.audit(params);
    }

    /**
     * 删除品牌
     */
    @RequirePermissions("goods:brand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public Result remove(@PathVariable Long... ids) {
        brandService.delete(ids);
        return ResultUtil.success();
    }

    /**
     * 连接品牌
     */
    @RequirePermissions("goods:brand:linkCategory")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PutMapping("/category")
    @ApiOperation("品牌关联分类")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "品牌ID", dataTypeClass = Long.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "categoryCodes", value = "分类Code列表", dataTypeClass = Long[].class, paramType = "body")
            }
    )
    public Result link(@RequestBody BrandQueryParams brandParams) {
        if (ObjectUtils.isEmpty(brandParams.getId())) {
            return ResultUtil.fail(ResponseCode.ARGUMENT_ERROR.getCode(),"品牌ID不能为空");
        }
        brandService.linkCategory(brandParams);
        return ResultUtil.success();
    }

    /**
     * 查询品牌
     */
    @RequirePermissions("goods:brand:query")
    @GetMapping("/category/{id}")
    @ApiOperation("品牌关联分类查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id", value = "品牌ID", dataTypeClass = Long.class, paramType = "path",required = true),
            }
    )
    public Result queryBrandCategory(@PathVariable Long id) {
        return ResultUtil.success(brandService.getBrandCategory(id));
    }


    /**
     * 查询品牌
     */
    @RequirePermissions("goods:brand:query")
    @GetMapping("/options")
    @ApiOperation("分类关联品牌查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "categoryCode", value = "分类Code", dataTypeClass = Long.class, paramType = "query",required = true),
            }
    )
    public Result options(@ModelAttribute BrandQueryParams params) {
        if (ObjectUtils.isEmpty(params.getCategoryCode())) {
            return ResultUtil.fail("categoryCode不能为空");
        }

        return ResultUtil.success(brandService.options(BrandQueryParams.builder()
                .categoryCode(params.getCategoryCode())
                .build()));
    }

}
