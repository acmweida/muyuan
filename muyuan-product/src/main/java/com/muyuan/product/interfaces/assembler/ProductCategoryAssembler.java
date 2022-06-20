package com.muyuan.product.interfaces.assembler;

import com.muyuan.common.core.bean.SelectTree;
import com.muyuan.product.domains.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProductCategoryAssembler
 * Description 商品分类转换类
 * @Author 2456910384
 * @Date 2022/6/10 9:36
 * @Version 1.0
 */
public class ProductCategoryAssembler {

    public static List<SelectTree> buildSelectTree(List<ProductCategory> productCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            selectTrees.add(new SelectTree(String.valueOf(productCategory.getId())
                    ,productCategory.getName()
                    ,productCategory.leaf()));
        }
        return selectTrees;
    }
}
