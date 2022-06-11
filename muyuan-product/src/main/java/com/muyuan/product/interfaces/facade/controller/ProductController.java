package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.product.domains.factories.ProductFactory;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.service.ProductDomainService;
import com.muyuan.product.domains.vo.ProductVO;
import com.muyuan.product.interfaces.assembler.ProductAssembler;
import com.muyuan.product.interfaces.dto.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product")
@Api(tags = {"商品接口"})
@RestController
public class ProductController {


    @PostMapping("/getProduct")
    @ApiOperation(value = "通过商店信息查询商品列表")
    public Result<List<ProductVO>> getProducts(@RequestBody @Validated ProductDTO productDTO) {
        List<Product> products = ProductFactory.createProductService(productDTO.convert()).queryProductsByShopInfo(productDTO);
        List<ProductVO> productVOs = ProductAssembler.buildProductVO(products);
        return ResultUtil.success(productVOs);
    }

    @Repeatable
    @PostMapping("/addProduct")
    @ApiOperation(value = "添加商品接口")
    Result addProduct(@RequestBody @Validated ProductDTO productDTO) {
        Product product = ProductAssembler.buildProduct(productDTO);
        ProductDomainService productDomainService = ProductFactory.createProductService(product);
        Optional<Long> aLong = productDomainService.addProduct();
        if (aLong.isPresent()) {
            return ResultUtil.success();
        }

        return ResultUtil.fail("商品添加失败");
    }

}
