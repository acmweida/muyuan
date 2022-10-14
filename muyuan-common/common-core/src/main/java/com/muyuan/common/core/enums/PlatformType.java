package com.muyuan.common.core.enums;

/**
 * 平台类型枚举
 */
public enum PlatformType {

    /**
     * 商户平台
     */
    MERCHANT,

    /**
     *  商城平台
     */
    MEMBER,

    /**
     * 运营平台
     */
    OPERATOR;


    public static PlatformType trance(Integer code) {
        switch (code) {
            case 0 : return OPERATOR;
            case 1 : return MERCHANT;
            case 2 :
            default: return MEMBER;
        }
    }

    public  int getCode() {
        switch (this) {
            case OPERATOR : return 0;
            case MERCHANT : return 1;
            case MEMBER:
            default: return 2;
        }
    }
}