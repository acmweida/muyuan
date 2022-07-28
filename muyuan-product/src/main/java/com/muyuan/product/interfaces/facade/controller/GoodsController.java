package com.muyuan.product.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.mybatis.jdbc.page.Page;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.product.application.GoodsApplicationService;
import com.muyuan.product.domains.dto.GoodsDTO;
import com.muyuan.product.domains.model.Goods;
import com.muyuan.product.domains.service.GoodsDomainService;
import com.muyuan.product.domains.vo.GoodsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/goods")
@Api(tags = {"商品接口"})
@RestController
@AllArgsConstructor
public class GoodsController {

    private GoodsDomainService goodsDomainService;

    private GoodsApplicationService goodsApplicationService;

    @ApiOperation(value = "通过商店信息查询商品列表")
    @RequirePermissions("product:goods:list")
    @GetMapping("/list")
    public Result<Page<GoodsVO>> getProducts(@ModelAttribute GoodsDTO goodsDTO) {
        Page<Goods> page = goodsDomainService.page(goodsDTO, SecurityUtils.getShopId());
        return ResultUtil.success(page);
    }

    @Repeatable
    @ApiOperation(value = "添加商品接口")
    @RequirePermissions("product:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    Result addProduct(@RequestBody @Validated GoodsDTO goodsDTO) {
        goodsApplicationService.addGoods(goodsDTO);
        return ResultUtil.success("商品添加成功");
    }

}
