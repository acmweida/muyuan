package com.muyuan.shop.interfaces.facade.controller.impl;

import com.muyuan.common.exception.handler.UserNotFoundException;
import com.muyuan.common.result.Result;
import com.muyuan.common.result.ResultUtil;
import com.muyuan.common.util.JwtUtils;
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
        final long currentUserId = JwtUtils.getUserId();
        log.info("user id: {} invoke /shop/getShopBaseInfo ",currentUserId);
        Optional<ShopVO> shopVo = shopQuery.getShopBaseInfoByyMemberId(currentUserId);
        shopVo.orElseThrow(() ->{
            log.warn("user info not found use user id :{}",currentUserId);
            return new UserNotFoundException();
        });
        return ResultUtil.render(shopVo.get());
    }
}
