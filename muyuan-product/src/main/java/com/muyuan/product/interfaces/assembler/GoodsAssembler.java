package com.muyuan.product.interfaces.assembler;

import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.vo.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsAssembler {


    public static GoodsVO buildProductVO(Goods product) {
        Assert.notNull(product,"product not be bull");
        return buildProductVO(Arrays.asList(product)).get(0);
    }


    public static List<GoodsVO> buildProductVO(List<Goods> goodsList) {
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
