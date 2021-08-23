package com.muyuan.member.enums;

/**
 * 用户类型枚举
 */
public enum UserType {

    BUSIENESS((short) 0,"会员"),
    MEMBER((short)1,"会员")
    ;

    UserType(short code, String name) {
        this.code = code;
        this.name = name;
    }

    public short getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    /**
     * 类型编码
     */
    private short code;

    /**
     * 类型
     */
    private String name;
}
