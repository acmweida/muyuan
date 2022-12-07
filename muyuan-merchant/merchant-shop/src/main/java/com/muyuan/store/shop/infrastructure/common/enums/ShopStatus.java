package com.muyuan.store.shop.infrastructure.common.enums;

/**
 * @ClassName ShopStatus
 * Description 店铺状态
 * @Author 2456910384
 * @Date 2022/7/21 10:49
 * @Version 1.0
 */
public enum ShopStatus {

    OK(0),
    APPLY(1),
    AUDITING(2),
    AUDIT_PASS(3),
    AUDIT_ERROR(4),
    DISABLE(5);

    public int code;

    ShopStatus(int code) {
        this.code = code;
    }
}
