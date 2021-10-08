package com.muyuan.product.domains.query;

import com.muyuan.product.domains.model.Product;
import com.muyuan.product.interfaces.dto.ShopProductDTO;

import java.util.List;

public interface ProductQuery {

    List<Product> queryProductsByShopInfo(ShopProductDTO shopProductDTO);
}
