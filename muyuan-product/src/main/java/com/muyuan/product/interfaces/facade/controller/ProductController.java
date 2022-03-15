package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.product.domains.vo.ProductVO;
import com.muyuan.product.interfaces.dto.ProductDTO;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/product")
@Api(tags = {"商品接口"})
@RestController
public interface ProductController {

    @PostMapping("/getProduct")
    @ApiOperation(value = "通过商店信息查询商品列表")
    Result<List<ProductVO>> getProducts(@RequestBody @Validated ShopProductDTO productDTO);

    @Repeatable
    @PostMapping("/addProduct")
    @ApiOperation(value = "添加商品接口")
    Result addProduct(@RequestBody @Validated ProductDTO productDTO);

}
