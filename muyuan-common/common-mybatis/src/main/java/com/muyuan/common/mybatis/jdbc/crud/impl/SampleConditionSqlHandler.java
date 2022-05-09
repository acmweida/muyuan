package com.muyuan.common.mybatis.jdbc.crud.impl;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.Condition;
import com.muyuan.common.mybatis.jdbc.crud.ConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.Option;

public class SampleConditionSqlHandler implements ConditionSqlHandler {

    @Override
    public String buildSql(Condition condition) {
        return " "+ StrUtil.humpToUnderline(condition.getField()) + condition.getOption() + "#{" + condition.getField()+"}";
    }

    @Override
    public boolean supper(Option op) {
        switch (op) {
            case EQ:
            case UNEQ:
            case LIKE:
                return true;
            default:return false;
        }
    }
}
