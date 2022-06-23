package com.muyuan.shop.interfaces.facade.controller;

import com.muyuan.common.core.result.Result;
import com.muyuan.shop.domains.vo.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@Api(tags = {"店铺基本信息接口"})
public interface ShopController {

    @GetMapping("/getShopBaseInfo")
    @ApiOperation(value = "通过Token获取店铺基本信息")
    Result<ShopVO> getShopBaseInfo();
}
