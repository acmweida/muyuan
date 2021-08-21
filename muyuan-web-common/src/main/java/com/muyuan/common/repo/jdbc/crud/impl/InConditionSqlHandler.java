package com.muyuan.common.repo.jdbc.crud.impl;

import com.muyuan.common.repo.jdbc.crud.Condition;
import com.muyuan.common.repo.jdbc.crud.ConditionSqlHandler;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Collection;

public class InConditionSqlHandler implements ConditionSqlHandler {

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
        return  condition.getField() +  condition.getOption().getOp() + inExpression(condition.getField(),size);
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
