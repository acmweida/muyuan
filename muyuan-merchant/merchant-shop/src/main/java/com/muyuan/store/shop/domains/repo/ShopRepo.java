package com.muyuan.store.shop.domains.repo;

import com.muyuan.common.mybatis.common.BaseRepo;
import com.muyuan.store.shop.domains.model.Shop;

public interface ShopRepo extends BaseRepo {

    void insert(Shop shop);

    void update(Shop shop);

}
