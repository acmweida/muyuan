package com.muyuan.common.mybatis.jdbc.crud;

public enum Option {
    EQ(" = "),UNEQ(" != "),
    IN(" in "),LIKE(" like "),
    GT(" > "),LT(" < "),
    GTE(" >= "),LTE(" <= "),
    OR("",false),
    ORDER("order by ",false),
    AND("and",false);

    public String getOp() {
        return op;
    }

    Option(String op) {
        this(op,true);
    }

    Option(String op,boolean parma) {
        this.op = op;
        this.parma = parma;
    }

    private String op;

    private boolean parma;

    public boolean isParma() {
        return parma;
    }
}
