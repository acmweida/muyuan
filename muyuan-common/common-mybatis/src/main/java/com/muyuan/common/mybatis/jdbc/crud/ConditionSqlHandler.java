package com.muyuan.common.mybatis.jdbc.crud;

public interface ConditionSqlHandler {

    String buildSql(Condition condition);

    boolean supper(Option op);
}
