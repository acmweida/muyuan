package com.muyuan.common.repo.jdbc.crud;


import lombok.Data;

@Data
public class Condition {

    private String field;

    private Object value;

    private Option option;

}
