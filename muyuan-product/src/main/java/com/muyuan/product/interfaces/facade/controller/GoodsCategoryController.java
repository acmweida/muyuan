package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.product.domains.dto.GoodsCategoryDTO;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.service.GoodsCategoryDomainService;
import com.muyuan.product.interfaces.assembler.GoodsCategoryAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
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
public class GoodsCategoryController {

    private GoodsCategoryDomainService productCategoryDomainService;

    @GetMapping("/list")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态", dataTypeClass = String.class, paramType = "query")}
    )
    public Result list(@ModelAttribute GoodsCategoryDTO categoryDTO) {
        List<GoodsCategory> list = productCategoryDomainService.list(categoryDTO);
        return ResultUtil.success(GoodsCategoryAssembler.buildListTree(list));
    }

    @PostMapping()
    @RequirePermissions("product:category:add")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "code", value = "商品分类编码", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "orderNum", value = "显示顺序", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "logo", value = "分类图标", dataTypeClass = String.class, paramType = "body")}
    )
    public Result add(@RequestBody @Valid GoodsCategoryDTO goodsCategoryDTO) {
        GoodsCategory category = goodsCategoryDTO.convert();
        if (GlobalConst.NOT_UNIQUE.equals(productCategoryDomainService.checkUnique(category))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST.getCode(),"商品分类已存在");
        }
        productCategoryDomainService.add(goodsCategoryDTO);
        return ResultUtil.success();
    }

    @PutMapping()
    @RequirePermissions("product:category:edit")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "商品分类ID", dataTypeClass = String.class, paramType = "body"),
            @ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "orderNum", value = "显示顺序", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "logo", value = "分类图标", dataTypeClass = String.class, paramType = "body")}
    )
    public Result update(@RequestBody @Valid GoodsCategoryDTO goodsCategoryDTO) {
        if (ObjectUtils.isEmpty(goodsCategoryDTO.getId())) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(),"更新分类ID不能为空");
        }

        GoodsCategory category = goodsCategoryDTO.convert();
        if (GlobalConst.NOT_UNIQUE.equals(productCategoryDomainService.checkUnique(category))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST.getCode(),"商品分类已存在");
        }

        productCategoryDomainService.update(goodsCategoryDTO);
        return ResultUtil.success();
    }


    @GetMapping("/treeSelect")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "parentId", value = "父节点ID", dataTypeClass = String.class, paramType = "body", defaultValue = "0")}
    )
    public Result treeSelect(@ModelAttribute GoodsCategoryDTO goodsCategoryDTO) {
        if (ObjectUtils.isEmpty(goodsCategoryDTO.getParentId())) {
            goodsCategoryDTO.setParentId(0l);
        }
        List<GoodsCategory> list = productCategoryDomainService.list(goodsCategoryDTO);
        return ResultUtil.success(GoodsCategoryAssembler.buildSelectTree(list));
    }

    @GetMapping("/{id}")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result get(@PathVariable @Valid @NotBlank(message = "id不能未空") String id) {
        Optional<GoodsCategory> productCategory = productCategoryDomainService.get(GoodsCategory.builder().id(Long.valueOf(id)).build());
        if (productCategory.isPresent()) {
            return ResultUtil.success(productCategory.get());
        }
        return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(),"商品分类信息未找到");
    }

    /**
     * 通过商品编码 获取三级目录信息
     * @param code
     * @return
     */
    @GetMapping("/3th/{code}")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "code", value = "商品编码", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result get3th(@PathVariable @Valid @NotBlank(message = "code不能未空") String code) {
        Optional<GoodsCategory> productCategory = productCategoryDomainService.get(GoodsCategory.builder()
                .code(Long.valueOf(code))
                .level(GlobalConst.GOODS_LAST_LEVEL)
                .build());
        if (productCategory.isPresent()) {
            return ResultUtil.success(productCategory.get());
        }
        return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(),"商品分类信息未找到");
    }

    @DeleteMapping("/{ids}")
    @RequirePermissions("product:category:remove")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result delete(@PathVariable String[] ids) {
        productCategoryDomainService.delete(ids);
        return ResultUtil.success();
    }
}
