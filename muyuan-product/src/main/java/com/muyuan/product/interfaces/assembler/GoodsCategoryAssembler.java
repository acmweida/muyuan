package com.muyuan.product.interfaces.assembler;

import com.muyuan.common.core.bean.SelectTree;
import com.muyuan.product.domains.model.GoodsCategory;
import com.muyuan.product.domains.vo.GoodsCategoryVO;
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
public class GoodsCategoryAssembler {

    public static List<SelectTree> buildSelectTree(List<GoodsCategory> productCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (GoodsCategory goodsCategory : productCategories) {
            selectTrees.add(new SelectTree(
                    String.valueOf(goodsCategory.getId())
                    , goodsCategory.getName()
                    , goodsCategory.leaf()
                    , goodsCategory.getLevel() == 3
            ));
        }
        return selectTrees;
    }

    public static List<SelectTree> buildSelect(List<GoodsCategory> productCategories) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (GoodsCategory goodsCategory : productCategories) {
            selectTrees.add(new SelectTree(
                    String.valueOf(goodsCategory.getCode())
                    , goodsCategory.getName()
                    , goodsCategory.leaf()
            ));
        }
        return selectTrees;
    }


    public static List<GoodsCategoryVO> buildListTree(List<GoodsCategory> productCategories) {
        List<GoodsCategoryVO> listTrees = new ArrayList<>();
        for (GoodsCategory goodsCategory : productCategories) {
            GoodsCategoryVO temp = new GoodsCategoryVO();
            BeanUtils.copyProperties(goodsCategory,temp);
            temp.setHasChildren(goodsCategory.hasChildren());
            listTrees.add(temp);
        }
        return listTrees;
    }
}
