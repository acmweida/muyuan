package com.muyuan.manager.product.domains.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.manager.product.domains.model.Category;
import com.muyuan.manager.product.domains.vo.CategoryVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ProductCategoryAssembler
 * Description 商品分类转换类
 * @Author 2456910384
 * @Date 2022/6/10 9:36
 * @Version 1.0
 */
public class CategoryAssembler {

    public static List<SelectTree> buildSelectTree(List<Category> productCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (Category category : productCategories) {
            selectTrees.add(new SelectTree(
                    category.getId()
                    , String.valueOf(category.getCode())
                    , category.getName()
                    , category.leaf()
                    , category.getLevel() == 3
                    , category.getParentId()
            ));
        }
        return selectTrees;
    }

    public static List<SelectTree> buildSelect(List<Category> productCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (Category category : productCategories) {
            selectTrees.add(new SelectTree(
                    String.valueOf(category.getCode())
                    , category.getName()
                    , category.leaf()
            ));
        }
        return selectTrees;
    }


    public static List<CategoryVO> buildListTree(List<Category> productCategories) {
        List<CategoryVO> listTrees = new ArrayList<>();
        for (Category category : productCategories) {
            CategoryVO temp = new CategoryVO();
            BeanUtils.copyProperties(category,temp);
            temp.setHasChildren(category.hasChildren());
            listTrees.add(temp);
        }
        return listTrees;
    }
}
