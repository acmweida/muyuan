package com.muyuan.store.shop.infrastructure.persistence.mapper;

import com.muyuan.store.shop.domains.model.Shop;
import com.muyuan.store.shop.infrastructure.config.mybatis.ShopBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName ShopMapper 接口
 * Description T_shop
 * @Author 2456910384
 * @Date 2022/7/26 10:39
 * @Version 1.0
 */
@Mapper
public interface ShopMapper extends ShopBaseMapper<Shop> {

}
