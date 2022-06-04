package com.muyuan.common.mybatis.jdbc.crud;


import lombok.Data;

@Data
public class Condition {

    private String field;

    private Object value;

    private String expression;

    private Option option;

    public Condition() {
    }

    public Condition(String field, String expression,Object value, Option option) {
        this.field = field;
        this.value = value;
        this.option = option;
        this.expression = expression;
    }
}
