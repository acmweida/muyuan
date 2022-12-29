package com.muyuan.manager.goods.dto.assembler;

import com.muyuan.common.bean.SelectTree;
import com.muyuan.goods.api.dto.FeatureDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GoodsCategoryAssembler
 * Description 特征量转换类
 * @Author 2456910384
 * @Date 2022/6/10 9:36
 * @Version 1.0
 */
public class FeatureAssembler {

    public static List<SelectTree> buildSelect(List<FeatureDTO> features) {
        List<SelectTree> selectTrees = new ArrayList<>();
        for (FeatureDTO feature : features) {
            selectTrees.add(new SelectTree(
                    feature.getId()
                    , feature.getName()
            ));
        }
        return selectTrees;
    }

}
