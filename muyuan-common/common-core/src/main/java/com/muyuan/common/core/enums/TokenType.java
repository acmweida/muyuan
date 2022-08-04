package com.muyuan.common.core.enums;

/**
 * @ClassName TokenType 枚举
 * Description 生成form tokne 用于重复校验
 * @Author 2456910384
 * @Date 2022/8/3 15:49
 * @Version 1.0
 */
public enum TokenType {
    DEFAULT(""),
    ADD_MENU("add:menu");

    private String value;

    TokenType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
