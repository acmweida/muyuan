package com.muyuan.common.repo.jdbc.crud.impl;

import com.muyuan.common.repo.jdbc.crud.Condition;
import com.muyuan.common.repo.jdbc.crud.ConditionSqlHandler;
import com.muyuan.common.repo.jdbc.crud.Option;

public class EqConditionSqlHandler implements ConditionSqlHandler {
    @Override
    public String buildSql(Condition condition) {
        return " "+condition.getField() + Option.EQ.getOp() + "#{" + condition.getField()+"}";
    }
}
