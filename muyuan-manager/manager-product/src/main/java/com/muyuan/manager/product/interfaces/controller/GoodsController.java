package com.muyuan.manager.product.interfaces.controller;

import com.muyuan.common.bean.Page;
import com.muyuan.common.bean.Result;
import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.enums.TokenType;
import com.muyuan.common.core.util.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.redis.util.TokenUtil;
import com.muyuan.common.web.annotations.Repeatable;
import com.muyuan.common.web.annotations.RequirePermissions;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.manager.product.domains.dto.GoodsDTO;
import com.muyuan.manager.product.domains.model.Goods;
import com.muyuan.manager.product.domains.service.GoodsService;
import com.muyuan.manager.product.domains.vo.GoodsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/goods")
@Api(tags = {"商品接口"})
@RestController
@AllArgsConstructor
public class GoodsController {

    private GoodsService goodsService;

    @ApiOperation(value = "通过商店信息查询商品列表")
    @RequirePermissions("product:goods:list")
    @GetMapping("/list")
    public Result<Page<GoodsVO>> list(@ModelAttribute GoodsDTO goodsDTO) {
        Page<Goods> page = goodsService.page(goodsDTO, SecurityUtils.getShopId());
        return ResultUtil.success(page);
    }


    @RequirePermissions("product:goods:add")
    @PostMapping("/token")
    @ApiOperation("token生成")
    public Result token() {
        String token = TokenUtil.generate(TokenType.ADD_GOODS);
        Map<String,String> res = new HashMap();
        res.put(GlobalConst.TOKEN,token);
        return ResultUtil.success(res);
    }

    @Repeatable(tokenType = TokenType.ADD_GOODS)
    @ApiOperation(value = "添加商品接口")
    @RequirePermissions("product:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "token", value = "token", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "name", value = "商品名称", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "brandId", value = "品牌ID", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "categoryCode", value = "分类编码", dataTypeClass = String.class, paramType = "body",required = true),
                    @ApiImplicitParam(name = "tags", value = "商品标签", dataTypeClass = String[].class, paramType = "body")
            }
    )
    public Result add(@RequestBody @Validated GoodsDTO goodsDTO) {
        goodsService.addGoods(goodsDTO);
        return ResultUtil.success("商品添加成功");
    }

}