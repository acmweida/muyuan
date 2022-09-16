package com.muyuan.common.core.enums;

/**
 * 用户类型枚举
 */
public enum UserType {

    /**
     * 商家用户（卖家）
     */
    MERCHANT,

    /**
     * 会员（买家）
     */
    MEMBER,

    /**
     * 管理员用户
     */
    OPERATOR;


    public static UserType trance(int code) {
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