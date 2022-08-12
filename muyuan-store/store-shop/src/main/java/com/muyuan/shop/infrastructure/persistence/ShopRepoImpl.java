package com.muyuan.shop.infrastructure.persistence;

import com.muyuan.shop.domains.model.Shop;
import com.muyuan.shop.domains.repo.ShopRepo;
import com.muyuan.shop.infrastructure.persistence.mapper.ShopMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ShopRepoImpl implements ShopRepo {

    private ShopMapper shopMapper;

    @Override
    public void insert(Shop shop) {
        shopMapper.insert(shop);
    }

    @Override
    public void update(Shop shop) {
        shopMapper.updateBy(shop, ShopRepo.ID);
    }
}
