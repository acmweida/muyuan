package com.muyuan.shop.infrastructure.common.enums;

/**
 * @ClassName ShopType 枚举
 * Description 店铺类型
 * @Author 2456910384
 * @Date 2022/7/21 10:10
 * @Version 1.0
 */
public enum ShopType {

    SELF(0), // 自营
    SETTlED(1), // 入驻
    OFFICIAL(2); // 官方

    public int code;

    ShopType(int code) {
        this.code = code;
    }
}
