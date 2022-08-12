package com.muyuan.shop.domains.repo;

import com.muyuan.common.core.constant.BaseRepo;
import com.muyuan.shop.domains.model.Shop;

public interface ShopRepo extends BaseRepo {

    void insert(Shop shop);

    void update(Shop shop);

}
