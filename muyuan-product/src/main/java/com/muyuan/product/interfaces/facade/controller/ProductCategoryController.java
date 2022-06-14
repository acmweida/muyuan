package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.product.domains.model.ProductCategory;
import com.muyuan.product.domains.service.ProductCategoryDomainService;
import com.muyuan.product.interfaces.dto.ProductCategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            {@ApiImplicitParam(name = "name",value = "商品分类名称",dataTypeClass = String.class,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "状态",dataTypeClass = String.class,paramType = "query")}
    )
    public Result list(@ModelAttribute ProductCategoryDTO categoryDTO) {
        List<ProductCategory> list = productCategoryDomainService.list(categoryDTO);
        return ResultUtil.success(list);
    }
}
