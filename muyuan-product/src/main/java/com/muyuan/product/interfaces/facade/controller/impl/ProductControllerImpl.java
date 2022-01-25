package com.muyuan.product.interfaces.facade.controller.impl;

import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.product.domains.factories.ProductFactory;
import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.query.ProductQuery;
import com.muyuan.product.domains.service.ProductService;
import com.muyuan.product.domains.vo.ProductVO;
import com.muyuan.product.interfaces.assembler.ProductAssembler;
import com.muyuan.product.interfaces.dto.ProductDTO;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import com.muyuan.product.interfaces.facade.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductControllerImpl implements ProductController {

    @Autowired
    ProductQuery productQuery;

    @Override
    public Result<List<ProductVO>> getProducts(ShopProductDTO productDTO) {
        List<Product> products = productQuery.queryProductsByShopInfo(productDTO);
        List<ProductVO> productVOs = ProductAssembler.buildProductVO(products);
        return ResultUtil.render(productVOs);
    }

    @Override
    public Result addProduct(ProductDTO productDTO) {
        Product product = ProductAssembler.buildProduct(productDTO);
        ProductService productService = ProductFactory.createProductService(product);
        Optional<Long> aLong = productService.addProduct();
        if (aLong.isPresent()) {
            return ResultUtil.render();
        }

        return ResultUtil.renderFail("商品添加失败");
    }
}
