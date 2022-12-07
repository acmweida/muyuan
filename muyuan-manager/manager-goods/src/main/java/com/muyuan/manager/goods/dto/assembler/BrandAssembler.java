package com.muyuan.manager.goods.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.manager.goods.model.Brand;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GoodsCategoryAssembler
 * Description 商品分类转换类
 * @Author 2456910384
 * @Date 2022/6/10 9:36
 * @Version 1.0
 */
public class BrandAssembler {

    public static List<SelectTree> buildSelect(List<Brand> brands) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (Brand brand : brands) {
            selectTrees.add(new SelectTree(
                    brand.getId()
                    , brand.getName()
            ));
        }
        return selectTrees;
    }

}
