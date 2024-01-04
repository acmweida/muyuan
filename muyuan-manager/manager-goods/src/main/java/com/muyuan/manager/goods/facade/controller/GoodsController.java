package com.muyuan.manager.goods.facade.controller;

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
import com.muyuan.manager.goods.dto.GoodsDTO;
import com.muyuan.manager.goods.dto.assembler.GoodsAssembler;
import com.muyuan.manager.goods.dto.vo.GoodsVO;
import com.muyuan.manager.goods.model.Goods;
import com.muyuan.manager.goods.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/goods")
@Tag(name = "商品接口")
@RestController
@AllArgsConstructor
public class GoodsController {

    private GoodsService goodsService;

    @Operation(summary = "通过商店信息查询商品列表")
    @RequirePermissions("goods:goods:list")
    @GetMapping("/list")
    public Result<Page<GoodsVO>> list(@ModelAttribute GoodsDTO goodsDTO) {
        Page<Goods> page = goodsService.page(goodsDTO, SecurityUtils.getShopId());
        return ResultUtil.success(Page.<GoodsVO>builder()
                .pageSize(page.getPageSize())
                .pageNum(page.getPageSize())
                .rows(GoodsAssembler.buildGoodsVO(page.getRows()))
                .build());
    }


    @RequirePermissions("goods:goods:add")
    @PostMapping("/token")
    @Operation(summary = "token生成")
    public Result token() {
        String token = TokenUtil.generate(TokenType.ADD_GOODS);
        Map<String,String> res = new HashMap();
        res.put(GlobalConst.TOKEN,token);
        return ResultUtil.success(res);
    }

    @Repeatable(tokenType = TokenType.ADD_GOODS)
    @Operation(summary = "添加商品接口")
    @RequirePermissions("goods:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    @Parameters(
            {
                    @Parameter(name = "token", description = "token",  in = ParameterIn.QUERY,required = true),
                    @Parameter(name = "name", description = "商品名称", in = ParameterIn.QUERY,required = true),
                    @Parameter(name = "brandId", description = "品牌ID",in = ParameterIn.QUERY,required = true),
                    @Parameter(name = "categoryCode", description = "分类编码",  in = ParameterIn.QUERY,required = true),
                    @Parameter(name = "tags", description = "商品标签",in = ParameterIn.QUERY)
            }
    )
    public Result add(@RequestBody @Validated GoodsDTO goodsDTO) {
        goodsService.addGoods(goodsDTO);
        return ResultUtil.success("商品添加成功");
    }

}