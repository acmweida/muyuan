package com.muyuan.manager.goods.domains.assembler;

import com.muyuan.manager.goods.domains.model.Goods;
import com.muyuan.manager.goods.domains.vo.GoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsAssembler {


    public static GoodsVO buildGoodsVO(Goods goods) {
        Assert.notNull(goods,"goods not be bull");
        return buildGoodsVO(Arrays.asList(goods)).get(0);
    }


    public static List<GoodsVO> buildGoodsVO(List<Goods> goodsList) {
        Assert.notNull(goodsList,"goodss not be bull");
        List<GoodsVO> goodsVOS = new ArrayList<>();
        for (Goods goods : goodsList) {
            GoodsVO goodsVO = new GoodsVO();
            BeanUtils.copyProperties(goods, goodsVO);
            goodsVOS.add(goodsVO);
        }
        return goodsVOS;
    }
}
