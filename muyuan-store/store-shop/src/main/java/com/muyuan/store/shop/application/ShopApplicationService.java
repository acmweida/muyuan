package com.muyuan.store.shop.application;

import com.muyuan.store.shop.domains.dto.ShopDTO;

/**
 * @ClassName ShopApplicationService 接口
 * Description 商品域服务
 * @Author 2456910384
 * @Date 2022/7/21 9:51
 * @Version 1.0
 */
public interface ShopApplicationService {

    void createSettledShop(ShopDTO shopDTO);
}
