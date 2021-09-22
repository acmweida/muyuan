package com.muyuan.shop.domain.query.impl;

import com.muyuan.shop.domain.query.ShopQuery;
import com.muyuan.shop.domain.vo.ShopVO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopQueryImpl implements ShopQuery {



    @Override
    public Optional<ShopVO> getShopBaseInfoByyMemberId(long memberId) {
        return Optional.empty();
    }
}
