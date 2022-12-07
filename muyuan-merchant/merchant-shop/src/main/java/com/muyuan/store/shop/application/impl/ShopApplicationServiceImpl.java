package com.muyuan.store.shop.application.impl;

import com.muyuan.common.core.constant.ServiceTypeConst;
import com.muyuan.common.core.enums.ResponseCode;
import com.muyuan.common.core.exception.MuyuanException;
import com.muyuan.common.web.util.SecurityUtils;
import com.muyuan.store.shop.application.ShopApplicationService;
import com.muyuan.store.shop.domains.dto.ShopDTO;
import com.muyuan.store.shop.domains.model.Shop;
import com.muyuan.store.shop.domains.service.ShopDomainService;
import com.muyuan.store.shop.infrastructure.common.enums.ShopType;
import com.muyuan.user.api.OperatorInterface;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName ShopApplicationServiceImpl
 * Description
 * @Author 2456910384
 * @Date 2022/7/21 9:52
 * @Version 1.0
 */
@Service
public class ShopApplicationServiceImpl implements ShopApplicationService {

    @DubboReference(group = ServiceTypeConst.MANAGER_SYSTEM, version = "1.0")
    private OperatorInterface operatorInterface;

    @Autowired
    private ShopDomainService shopDomainService;

    @Override
    @Transactional
    public void createSettledShop(ShopDTO shopDTO) {
        Shop shop = shopDTO.convert();
        if (ObjectUtils.isEmpty(SecurityUtils.getShopId())) {
            shopDomainService.addShop(shop, ShopType.SETTlED);

//            userInterface.linkShop(shop.getId());
        }else {
            throw new MuyuanException(ResponseCode.FAIL.getCode(), "该账户已绑定店铺");
        }
    }
}
