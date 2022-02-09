package com.muyuan.common.mybatis.jdbc.crud.impl;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.Condition;
import com.muyuan.common.mybatis.jdbc.crud.ConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.Option;

public class EqConditionSqlHandler implements ConditionSqlHandler {
    @Override
    public String buildSql(Condition condition) {
        return " "+ StrUtil.humpToUnderline(condition.getField()) + Option.EQ.getOp() + "#{" + condition.getField()+"}";
    }
}
