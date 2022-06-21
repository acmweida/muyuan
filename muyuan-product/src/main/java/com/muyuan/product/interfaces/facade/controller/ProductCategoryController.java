package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.domains.service.ProductCategoryDomainService;
import com.muyuan.product.interfaces.assembler.ProductCategoryAssembler;
import com.muyuan.product.interfaces.dto.ProductCategoryDTO;
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
public class ProductCategoryController {

    private ProductCategoryDomainService productCategoryDomainService;

    @GetMapping("/list")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "query"),
                    @ApiImplicitParam(name = "status", value = "状态", dataTypeClass = String.class, paramType = "query")}
    )
    public Result list(@ModelAttribute ProductCategoryDTO categoryDTO) {
        List<ProductCategory> list = productCategoryDomainService.list(categoryDTO);
        return ResultUtil.success(list);
    }

    @PostMapping()
    @RequirePermissions("product:category:add")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "name", value = "商品分类名称", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "code", value = "商品分类编码", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "orderNum", value = "显示顺序", dataTypeClass = String.class, paramType = "body"),
                    @ApiImplicitParam(name = "logo", value = "分类图标", dataTypeClass = String.class, paramType = "body")}
    )
    public Result add(@RequestBody @Valid ProductCategoryDTO productCategoryDTO) {
        ProductCategory category = productCategoryDTO.convert();
        if (GlobalConst.NOT_UNIQUE.equals(productCategoryDomainService.checkUnique(category))) {
            return ResultUtil.fail(ResponseCode.ADD_EXIST.getCode(),"商品分类已存在");
        }
        productCategoryDomainService.add(productCategoryDTO);
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
    public Result update(@RequestBody @Valid ProductCategoryDTO productCategoryDTO) {
        if (ObjectUtils.isEmpty(productCategoryDTO.getId())) {
            return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST.getCode(),"更新分类ID不能为空");
        }

        productCategoryDomainService.update(productCategoryDTO);
        return ResultUtil.success();
    }


    @GetMapping("/treeSelect")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "parentId", value = "父节点ID", dataTypeClass = String.class, paramType = "body", defaultValue = "0")}
    )
    public Result treeSelect(@ModelAttribute ProductCategoryDTO productCategoryDTO) {
        if (ObjectUtils.isEmpty(productCategoryDTO.getParentId())) {
            productCategoryDTO.setParentId(0l);
        }
        List<ProductCategory> list = productCategoryDomainService.list(productCategoryDTO);
        return ResultUtil.success(ProductCategoryAssembler.buildSelectTree(list));
    }

    @GetMapping("/{id}")
    @RequirePermissions("product:category:query")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "id", value = "ID", dataTypeClass = Long.class, paramType = "path")}
    )
    public Result get(@PathVariable @Valid @NotBlank(message = "id不能未空") String id) {
        Optional<ProductCategory> productCategory = productCategoryDomainService.get(id);
        if (productCategory.isPresent()) {
            return ResultUtil.success(productCategory.get());
        }
        return ResultUtil.fail(ResponseCode.QUERY_NOT_EXIST,"商品分类信息未找到");
    }
}
