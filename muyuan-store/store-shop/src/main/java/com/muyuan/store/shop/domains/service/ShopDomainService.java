package com.muyuan.store.shop.domains.service;

import com.muyuan.store.shop.domains.dto.ShopDTO;
import com.muyuan.store.shop.domains.model.Shop;
import com.muyuan.store.shop.domains.vo.ShopVO;
import com.muyuan.store.shop.infrastructure.common.enums.ShopType;

import java.util.Optional;

/**
 * @ClassName ShopDomainService 接口
 * Description 店铺信息服务
 * @Author 2456910384
 * @Date 2022/7/21 9:37
 * @Version 1.0
 */
public interface ShopDomainService {

    Optional<ShopVO> getShopBaseInfoByyMemberId(Long id);


    void addShop(ShopDTO shopDTO, ShopType shopType);

    void addShop(Shop Shop, ShopType shopType);
}
