package com.muyuan.shop.interfaces.facade.controller.impl;

import com.muyuan.common.core.exception.handler.UserNotFoundException;
import com.muyuan.common.core.result.Result;
import com.muyuan.common.core.result.ResultUtil;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.shop.domain.query.ShopQuery;
import com.muyuan.shop.domain.vo.ShopVO;
import com.muyuan.shop.interfaces.facade.controller.ShopController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ShopControllerImpl implements ShopController {

    @Autowired
    ShopQuery shopQuery;

    @Override
    public Result<ShopVO> getShopBaseInfo() {
        final long currentUserId = SecurityUtils.getUserId();
        log.info("user id: {} invoke /shop/getShopBaseInfo ",currentUserId);
        Optional<ShopVO> shopVo = shopQuery.getShopBaseInfoByyMemberId(currentUserId);
        shopVo.orElseThrow(() ->{
            log.warn("user info not found use user id :{}",currentUserId);
            return new UserNotFoundException();
        });
        return ResultUtil.success(shopVo.get());
    }
}
