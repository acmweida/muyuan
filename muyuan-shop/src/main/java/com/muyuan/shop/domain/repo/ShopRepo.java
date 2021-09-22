package com.muyuan.shop.domain.repo;

import com.muyuan.shop.domain.model.Shop;

public interface ShopRepo {

    Shop getShopByShopId(long shopId);
}
