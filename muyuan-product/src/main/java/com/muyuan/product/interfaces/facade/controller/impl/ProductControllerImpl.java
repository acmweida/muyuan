package com.muyuan.product.interfaces.facade.controller.impl;

import com.muyuan.common.result.Result;
import com.muyuan.product.domains.query.ProductQuery;
import com.muyuan.product.domains.vo.ProductVO;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import com.muyuan.product.interfaces.facade.controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductControllerImpl implements ProductController {

    @Autowired
    ProductQuery productQuery;

    @Override
    public Result<List<ProductVO>> getProducts(ShopProductDTO productDTO) {

        return null;
    }
}
