package com.muyuan.common.mybatis.jdbc.crud;

public enum Option {
    EQ(" = ",(short) 1),UNEQ(" != ",(short) 2),
    IN(" in ", (short) 3),LIKE(" like ",(short) 4),
    PAGE("page ",(short) 5),OR("",(short) 6),
    AND("and",(short) 7);

    public String getOp() {
        return op;
    }

    public short getCode() {
        return code;
    }

    Option(String op, short code) {
        this.op = op;
        this.code = code;
    }

    private String op;

    private short code;

}
