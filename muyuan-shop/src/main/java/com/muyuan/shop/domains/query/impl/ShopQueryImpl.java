package com.muyuan.shop.domains.query.impl;

import com.muyuan.shop.domains.query.ShopQuery;
import com.muyuan.shop.domains.vo.ShopVO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopQueryImpl implements ShopQuery {



    @Override
    public Optional<ShopVO> getShopBaseInfoByyMemberId(long memberId) {
        return Optional.empty();
    }
}
