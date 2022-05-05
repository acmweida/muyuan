package com.muyuan.common.mybatis.jdbc.crud;


import lombok.Data;

@Data
public class Condition {

    private String field;

    private Object value;

    private Option option;

    public Condition() {
    }

    public Condition(String field, Object value, Option option) {
        this.field = field;
        this.value = value;
        this.option = option;
    }
}
