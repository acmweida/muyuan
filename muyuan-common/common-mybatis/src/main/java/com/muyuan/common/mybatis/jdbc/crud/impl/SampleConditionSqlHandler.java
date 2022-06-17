package com.muyuan.common.mybatis.jdbc.crud.impl;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.Condition;
import com.muyuan.common.mybatis.jdbc.crud.ConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.Option;

public class SampleConditionSqlHandler implements ConditionSqlHandler {

    @Override
    public String buildSql(Condition condition) {
        if (Option.LIKE.equals(condition.getOption())) {
            return " " + StrUtil.humpToUnderline(condition.getField()) + condition.getOption().getOp() + "'#{" + condition.getExpression() + "}'%";
        }

        return " " + StrUtil.humpToUnderline(condition.getField()) + condition.getOption().getOp() + "#{" + condition.getExpression() + "}";
    }

    @Override
    public boolean suppert(Option op) {
        switch (op) {
            case EQ:
            case UNEQ:
            case LIKE:
            case LT:
            case LTE:
            case GT:
            case GTE:
                return true;
            default:
                return false;
        }
    }
}
