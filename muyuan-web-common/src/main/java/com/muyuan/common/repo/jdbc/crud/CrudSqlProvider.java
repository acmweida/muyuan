package com.muyuan.common.repo.jdbc.crud;

import com.muyuan.common.repo.jdbc.crud.impl.EqConditionSqlHandler;
import com.muyuan.common.repo.jdbc.crud.impl.InConditionSqlHandler;
import com.muyuan.common.util.InternalStrUtil;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
public class CrudSqlProvider {

    private static ConcurrentHashMap<Option, ConditionSqlHandler> sqlHandlers = new ConcurrentHashMap<>();

    static {
        sqlHandlers.put(Option.EQ,new EqConditionSqlHandler());
        sqlHandlers.put(Option.IN,new InConditionSqlHandler());
    }

    public String selectOne(Map<String,Object> params) {
        SQL sql = new SQL();

        String table = (String) params.get(Constant.TABLE_NAME);
        String[] column = (String[]) params.get(Constant.COLUMN);
        sql.SELECT(column).FROM(Constant.TABLE_PREFIX + table);
        String conditionSql ;
        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            Option option = condition.getOption();
            if (option != Option.OR  && option != Option.AND) {
                ConditionSqlHandler conditionSqlHandler = sqlHandlers.get(option);
                if (null == conditionSqlHandler) {
                    throw new RuntimeException("sql condition conditionSqlHandler is null for option '"+condition.getOption().getOp()+"'");
                }
                conditionSql = conditionSqlHandler.buildSql(condition);
                conditionSqls.add(conditionSql);
            } else {
                sql.WHERE( conditionSqls.toArray(new String[conditionSqls.size()]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else if (option == Option.AND) {
                    sql.AND();
                }
            }
        }
        if (!conditions.isEmpty()) {
            sql.WHERE( conditionSqls.toArray(new String[conditionSqls.size()]));
            sql.LIMIT(1);
        }

        return sql.toString();
    }

    public String selectList(Map<String,Object> params) {
        SQL sql = new SQL();

        String table = (String) params.get(Constant.TABLE_NAME);
        String[] column = (String[]) params.get(Constant.COLUMN);
        sql.SELECT(column).FROM(Constant.TABLE_PREFIX + table);
        String conditionSql ;
        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            Option option = condition.getOption();
            if (option != Option.OR  && option != Option.AND) {
                ConditionSqlHandler conditionSqlHandler = sqlHandlers.get(option);
                if (null == conditionSqlHandler) {
                    throw new RuntimeException("sql condition conditionSqlHandler is null for option '"+condition.getOption().getOp()+"'");
                }
                conditionSql = conditionSqlHandler.buildSql(condition);
                conditionSqls.add(conditionSql);
            } else {
                sql.WHERE( conditionSqls.toArray(new String[conditionSqls.size()]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else if (option == Option.AND) {
                    sql.AND();
                }
            }
        }
        if (!conditions.isEmpty()) {
            sql.WHERE( conditionSqls.toArray(new String[conditionSqls.size()]));
        }

        return sql.toString();
    }

    public String insert(Object bean) {
        SQL sql = new SQL();
        Class<?> aClass = bean.getClass();
        String simpleName = aClass.getSimpleName();
        sql.INSERT_INTO(Constant.TABLE_PREFIX+InternalStrUtil.humpToUnderline(simpleName));

        List<String> values = new ArrayList<>();
        List<String> column = new ArrayList<>();
        aClass.getDeclaredMethods();

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field propertyDescriptor : declaredFields) {
            propertyDescriptor.setAccessible(true);
            Object field = ReflectionUtils.getField(propertyDescriptor, bean);
            if (null != field) {
                values.add("#{" + propertyDescriptor.getName() + "}");
                column.add(InternalStrUtil.humpToUnderline(propertyDescriptor.getName()));
            }
        }


        sql.VALUES(StringUtils.arrayToDelimitedString(column.toArray(new String[column.size()]),","),
                StringUtils.arrayToDelimitedString(values.toArray(new String[column.size()]),",") );
        return sql.toString();
    }


}