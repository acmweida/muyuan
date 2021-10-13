package com.muyuan.product.interfaces.assembler;

import com.muyuan.product.domains.model.Product;
import com.muyuan.product.domains.vo.ProductVO;
import com.muyuan.product.interfaces.dto.ProductDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductAssembler {


    public static ProductVO buildProductVO(Product product) {
        Assert.notNull(product,"product not be bull");
        return buildProductVO(Arrays.asList(product)).get(0);
    }

    public static Product buildProduct(ProductDTO productDTO) {
        Assert.notNull(productDTO,"productDTO not be bull");
        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        return product;
    }

    public static List<ProductVO> buildProductVO(List<Product> products) {
        Assert.notNull(products,"products not be bull");
        List<ProductVO> productVOs = new ArrayList<>();
        for (Product product : products) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product,productVO);
            productVOs.add(productVO);
        }
        return productVOs;
    }
}
