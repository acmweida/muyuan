package com.muyuan.manager.goods.facade.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.goods.api.dto.CategoryDTO;
import com.muyuan.manager.goods.dto.CategoryParams;
import com.muyuan.manager.goods.dto.CategoryQueryParams;
import com.muyuan.manager.goods.dto.assembler.CategoryAssembler;
import com.muyuan.manager.goods.dto.converter.CategoryConverter;
import com.muyuan.manager.goods.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * @ClassName CategoryController
 * Description 商品类目控制器
 * @Author 2456910384
 * @Date 2022/6/9 15:27
 * @Version 1.0
 */
@RequestMapping("/category")
@Api(tags = {"商品类目接口"})
@RestController
@AllArgsConstructor
public class CategoryController {

    private CategoryConverter converter;

    private CategoryService goodsCategoryService;

    @GetMapping("/list")
    @RequirePermissions("goods:category:query")
//    //    @ApiOperationSupport(includeParameters = {"name","status"})
    public Result list(@ModelAttribute CategoryQueryParams params) {
        Page<CategoryDTO> list = goodsCategoryService.list(params);
        return ResultUtil.success(converter.toVO(list.getRows()));
    }

    @PostMapping()
    @RequirePermissions("goods:category:add")
    //    //    @ApiOperationSupport(ignoreParameters = "id")
    public Result add(@RequestBody @Validated(CategoryParams.Add.class) CategoryParams params) {
        return  goodsCategoryService.add(converter.to(params));
    }

    @PutMapping()
    @ApiOperation("商品分类更新")
    @RequirePermissions("goods:category:edit")
    public Result update(@RequestBody @Validated(CategoryParams.Update.class) CategoryParams params) {
        return ResultUtil.success(goodsCategoryService.update(converter.to(params)));
    }


    @GetMapping("/treeSelect")
    @RequirePermissions("goods:category:query")
    @ApiOperation("商品分类树型结构查询")
//    //    @ApiOperationSupport(includeParameters = {"parentId","level"})
    public Result treeSelect(@ModelAttribute CategoryQueryParams params) {
        if (ObjectUtils.isEmpty(params.getParentId()) && ObjectUtils.isEmpty(params.getLevel())) {
            params.setParentId(0L);
        }

        Page<CategoryDTO> list = goodsCategoryService.list(CategoryQueryParams.builder()
                .parentId(params.getParentId())
                .level(params.getLevel())
                .build());

        return ResultUtil.success(CategoryAssembler.buildSelectTree(list.getRows()));
    }

    @ApiOperation("商品分类简单查询")
    @GetMapping("/{id}")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path",required = true)}
    )
    public Result get(@PathVariable @Validated @NotBlank(message = "id不能未空") Long id) {
        Optional<CategoryDTO> goodsCategory = goodsCategoryService.get(id);
        return goodsCategory.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    @ApiOperation("商品分类详细查询")
    @GetMapping("/detail/{code}")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "商品编码", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result detail(@PathVariable @Validated @NotBlank(message = "code不能未空") Long code) {
        Optional<CategoryDTO> goodsCategory = goodsCategoryService.detail(code);
        return goodsCategory.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST));
    }

    /**
     * 通过商品编码 获取三级分类信息
     * @param code
     * @return
     */
    @ApiOperation("通过商品编码 获取三级分类信息")
    @GetMapping("/leaf/{code}")
    @RequirePermissions("goods:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "商品编码", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result getLeaf(@PathVariable @Validated @NotBlank(message = "code不能未空") Long code) {
        Optional<CategoryDTO> goodsCategory = goodsCategoryService.detail(code);
        return goodsCategory.map(ResultUtil::success)
                .orElseGet(() -> ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(), "商品分类信息未找到"));
    }

    /**
     * 通过商品编码 获取三级分类信息
     * @return
     */
    @ApiOperation("列表查询所有最终分类")
    @GetMapping("/leaf/selectOption")
    @RequirePermissions("goods:category:query")
    public Result selectOption() {
        Page<CategoryDTO> goodsCategorys = goodsCategoryService.list(CategoryQueryParams.builder()
                .leaf(GlobalConst.TRUE)
                .status(0)
                .build());
        return ResultUtil.success(CategoryAssembler.buildSelect(goodsCategorys.getRows()));
    }

    @ApiOperation("商品分类删除")
    @DeleteMapping("/{ids}")
    @RequirePermissions("goods:category:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result delete(@PathVariable Long ids) {
        goodsCategoryService.delete(ids);
        return ResultUtil.success();
    }

}
