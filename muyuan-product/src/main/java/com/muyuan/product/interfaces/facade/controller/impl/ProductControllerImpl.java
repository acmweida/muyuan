package com.muyuan.product.interfaces.facade.controller.impl;

import com.muyuan.common.result.Result;
import com.muyuan.product.domains.vo.ProductVO;
import com.muyuan.product.interfaces.dto.ShopProductDTO;
import com.muyuan.product.interfaces.facade.controller.ProductController;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductControllerImpl implements ProductController {
    @Override
    public Result<List<ProductVO>> getProduct(ShopProductDTO productDTO) {

        return null;
    }
}
