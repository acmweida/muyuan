package com.muyuan.shop.domain.query;

import com.muyuan.shop.domain.vo.ShopVO;

import java.util.Optional;

public interface ShopQuery {

    /**
     * 通过会员ID查询店铺信息
     * @param memberId
     * @return
     */
    Optional<ShopVO> getShopBaseInfoByyMemberId(long memberId);
}
