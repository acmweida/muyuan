package com.muyuan.store.shop.domains.service.impl;

import com.muyuan.store.shop.domains.dto.ShopDTO;
import com.muyuan.store.shop.domains.model.Shop;
import com.muyuan.store.shop.domains.repo.ShopRepo;
import com.muyuan.store.shop.domains.service.ShopDomainService;
import com.muyuan.store.shop.domains.vo.ShopVO;
import com.muyuan.store.shop.infrastructure.common.enums.ShopType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @ClassName ShopDomainServiceImpl
 * Description
 * @Author 2456910384
 * @Date 2022/7/21 9:38
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class ShopDomainServiceImpl implements ShopDomainService {

    private ShopRepo shopRepo;

    @Override
    public Optional<ShopVO> getShopBaseInfoByyMemberId(Long id) {


        return Optional.empty();
    }

    @Override
    @Transactional
    public void addShop(ShopDTO shopDTO, ShopType shopType) {
        Shop shop = shopDTO.convert();
        addShop(shop,shopType);
    }

    public void addShop(Shop shop,ShopType shopType) {
        shop.initInstance(shopType);
        shop.save(shopRepo);
    }

}
