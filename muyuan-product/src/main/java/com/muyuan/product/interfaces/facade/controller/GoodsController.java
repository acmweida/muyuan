package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.factories.GoodsFactory;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.service.GoodsDomainService;
import com.muyuan.product.domains.vo.GoodsVO;
import com.muyuan.product.interfaces.assembler.GoodsAssembler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/goods")
@Api(tags = {"商品接口"})
@RestController
public class GoodsController {


    @PostMapping("/getProduct")
    @ApiOperation(value = "通过商店信息查询商品列表")
    public Result<List<GoodsVO>> getProducts(@RequestBody @Validated GoodsDTO goodsDTO) {
        List<Goods> products = GoodsFactory.createProductService(goodsDTO.convert()).queryGoodsByShopInfo(goodsDTO);
        List<GoodsVO> goodsVOS = GoodsAssembler.buildProductVO(products);
        return ResultUtil.success(goodsVOS);
    }

    @Repeatable
    @PostMapping("/addProduct")
    @ApiOperation(value = "添加商品接口")
    Result addProduct(@RequestBody @Validated GoodsDTO goodsDTO) {
        Goods goods = GoodsAssembler.buildProduct(goodsDTO);
        GoodsDomainService goodsDomainService = GoodsFactory.createProductService(goods);
        Optional<Long> aLong = goodsDomainService.addGoods();
        if (aLong.isPresent()) {
            return ResultUtil.success();
        }

        return ResultUtil.fail("商品添加失败");
    }

}
