package com.muyuan.common.mybatis.jdbc.crud.impl;

import com.muyuan.common.mybatis.jdbc.crud.Condition;
import com.muyuan.common.mybatis.jdbc.crud.ConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.Option;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Collection;

public class CollectionConditionSqlHandler implements ConditionSqlHandler {

    @Override
    public String buildSql(Condition condition) {
        if (null == condition.getValue()) {
            return "";
        }
        int size = 0;
        Object obj = condition.getValue();

        if (obj.getClass().isArray()) {
            size = Array.getLength(obj);
        }
        if (obj instanceof Collection) {
            size = ((Collection<?>) obj).size();
        }
        if (0 == size) {
            return "";
        }
        return condition.getField() + condition.getOption().getOp() + inExpression(condition.getField(), size);
    }

    @Override
    public boolean suppert(Option op) {
        switch (op) {
            case IN:
                return true;
            default:
                return false;
        }
    }

    private String inExpression(String property, int size) {
        MessageFormat messageFormat = new MessageFormat("#'{'" + property + "[{0}]}");
        StringBuilder sb = new StringBuilder(" (");
        for (int i = 0; i < size; i++) {
            sb.append(messageFormat.format(new Object[]{i}));
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.append(")").toString();
    }
}
