package com.muyuan.common.repo.jdbc.crud.impl;

import com.muyuan.common.repo.jdbc.crud.Condition;
import com.muyuan.common.repo.jdbc.crud.ConditionSqlHandler;
import com.muyuan.common.repo.jdbc.crud.Option;
import com.muyuan.common.util.InternalStrUtil;

public class EqConditionSqlHandler implements ConditionSqlHandler {
    @Override
    public String buildSql(Condition condition) {
        return " "+ InternalStrUtil.humpToUnderline(condition.getField()) + Option.EQ.getOp() + "#{" + condition.getField()+"}";
    }
}
