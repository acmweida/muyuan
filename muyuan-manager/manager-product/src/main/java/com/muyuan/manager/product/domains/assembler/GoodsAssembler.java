package com.muyuan.manager.product.domains.assembler;

import com.muyuan.manager.product.domains.model.Goods;
import com.muyuan.manager.product.domains.vo.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsAssembler {


    public static GoodsVO buildGoodsVO(Goods product) {
        Assert.notNull(product,"product not be bull");
        return buildGoodsVO(Arrays.asList(product)).get(0);
    }


    public static List<GoodsVO> buildGoodsVO(List<Goods> goodsList) {
        Assert.notNull(goodsList,"products not be bull");
        List<GoodsVO> goodsVOS = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
            goodsVOS.add(goodsVO);
        }
        return goodsVOS;
    }
}
