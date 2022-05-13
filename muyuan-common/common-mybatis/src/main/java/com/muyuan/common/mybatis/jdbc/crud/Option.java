package com.muyuan.common.mybatis.jdbc.crud;

public enum Option {
    EQ(" = "),UNEQ(" != "),
    IN(" in "),LIKE(" like "),
    GT(" > "),LT(" < "),
    GTE(" >= "),LTE(" <= "),
    PAGE("page "),OR(""),
    ORDER("order"),
    AND("and");

    public String getOp() {
        return op;
    }

    Option(String op) {
        this.op = op;
    }

    private String op;

    private short code;

}
