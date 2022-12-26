package com.muyuan.manager.goods.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.goods.api.dto.CategoryDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GoodsCategoryAssembler
 * Description 商品分类转换类
 * @Author 2456910384
 * @Date 2022/6/10 9:36
 * @Version 1.0
 */
public class CategoryAssembler {

    public static List<SelectTree> buildSelectTree(List<CategoryDTO> goodsCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (CategoryDTO category : goodsCategories) {
            selectTrees.add(new SelectTree(
                    category.getId()
                    , String.valueOf(category.getCode())
                    , category.getName()
                    , category.getLeaf()
                    , category.getLevel() == 3
                    , category.getParentId()
            ));
        }
        return selectTrees;
    }

    public static List<SelectTree> buildSelect(List<CategoryDTO> goodsCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (CategoryDTO category : goodsCategories) {
            selectTrees.add(new SelectTree(
                    String.valueOf(category.getCode())
                    , category.getName()
                    , category.getLeaf()
            ));
        }
        return selectTrees;
    }

}
