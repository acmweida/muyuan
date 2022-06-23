package com.muyuan.shop.domains.repo;

import com.muyuan.shop.domains.model.Shop;

public interface ShopRepo {

    Shop getShopByShopId(long shopId);
}
