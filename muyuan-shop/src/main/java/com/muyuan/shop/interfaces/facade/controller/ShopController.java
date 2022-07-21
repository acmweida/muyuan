package com.muyuan.shop.interfaces.facade.controller;

import com.muyuan.common.core.exception.handler.UserNotFoundException;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.log.annotion.Log;
import com.muyuan.common.log.enums.BusinessType;
import com.muyuan.common.log.enums.OperatorType;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.shop.application.ShopApplicationService;
import com.muyuan.shop.domains.dto.ShopDTO;
import com.muyuan.shop.domains.service.ShopDomainService;
import com.muyuan.shop.domains.vo.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/shop")
@Api(tags = {"店铺基本信息接口"})
@Slf4j
@AllArgsConstructor
public class ShopController {

    private ShopDomainService shopDomainService;

    private ShopApplicationService applicationService;

    @GetMapping("/getShopBaseInfo")
    @ApiOperation(value = "通过Token获取店铺基本信息")
    public Result<ShopVO> getShopBaseInfo() {
        final long currentUserId = SecurityUtils.getUserId();
        log.info("user id: {} invoke /shop/getShopBaseInfo ",currentUserId);
        Optional<ShopVO> shopVo = shopDomainService.getShopBaseInfoByyMemberId(currentUserId);
        shopVo.orElseThrow(() ->{
            log.warn("user info not found use user id :{}",currentUserId);
            return new UserNotFoundException();
        });
        return ResultUtil.success(shopVo.get());
    }

    @PostMapping("/create")
    @Log(title = "店铺入驻",businessType = BusinessType.INSERT,operatorType = OperatorType.WEB)
    @ApiOperation("店铺入住 - 设置店铺信息")
    public Result createSettledShop(@RequestBody @Validated ShopDTO shopDTO) {
        applicationService.createSettledShop(shopDTO);
        return ResultUtil.success();
    }

}
