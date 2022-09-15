package com.muyuan.common.core.enums;

/**
 * 用户类型枚举
 */
public enum UserType {

    /**
     * 商家用户（卖家）
     */
    MERCHANT(1),

    /**
     * 会员（买家）
     */
    MEMBER(2),

    /**
     * 管理员用户
     */
    OPERATOR(0);

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public UserType trance(int code) {
        switch (code) {
            case 0 : return OPERATOR;
            case 1 : return MERCHANT;
            case 2 :
            default: return MEMBER;
        }
    }

    public int getCode() {
        return code;
    }
}