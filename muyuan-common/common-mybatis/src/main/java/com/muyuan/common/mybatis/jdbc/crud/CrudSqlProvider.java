package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.impl.CollectionConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.impl.SampleConditionSqlHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@SuppressWarnings("unchecked")
@Slf4j
public class CrudSqlProvider {

    private static List<ConditionSqlHandler> sqlHandlers = new ArrayList<>();

    static {
        sqlHandlers.add(new SampleConditionSqlHandler());
        sqlHandlers.add(new CollectionConditionSqlHandler());
    }

    public String selectOne(Map<String, Object> params, ProviderContext context) {
        SQL sql = new SQL();

        String[] column = (String[]) params.get(Constant.COLUMN);
        sql.SELECT(column).FROM(tableName(context));
        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            Option option = condition.getOption();
            if (option == Option.PAGE) {
                continue;
            }
            if (option != Option.OR && option != Option.AND) {
                conditionSqls.add(buildSql(condition));
            } else {
                sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else if (option == Option.AND) {
                    sql.AND();
                }
            }
        }
        if (!conditions.isEmpty()) {
            sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));
            sql.LIMIT(1);
        }

        log.info("select sql:{}",sql);

        return sql.toString();
    }

    public String selectList(Map<String, Object> params, ProviderContext context) {
        SQL sql = new SQL();

        List<String> orderBY = new ArrayList();
        String[] column = (String[]) params.get(Constant.COLUMN);
        sql.SELECT(column).FROM(tableName(context));
        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            Option option = condition.getOption();
            if (option == Option.PAGE) {
                continue;
            }
            if (option == option.ORDER) {
                orderBY.add((String) condition.getValue());
                continue;
            }
            if (option != Option.OR && option != Option.AND) {
                conditionSqls.add(buildSql(condition));
            } else {
                sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else if (option == Option.AND) {
                    sql.AND();
                }
            }
        }

        if (!conditions.isEmpty()) {
            sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));
        }

        if (!ObjectUtils.isEmpty(orderBY)) {
            sql.ORDER_BY(StringUtils.arrayToDelimitedString(orderBY.stream().toArray(), "m"));
        }

        log.info("select sql:{}",sql);
        return sql.toString();
    }

    public String insert(Object bean, ProviderContext context) {
        SQL sql = new SQL();
        sql.INSERT_INTO(tableName(context));

        List<String> values = new ArrayList<>();
        List<String> column = new ArrayList<>();

        Field[] declaredFields = entityType(context).getDeclaredFields();
        for (Field propertyDescriptor : declaredFields) {
            propertyDescriptor.setAccessible(true);
            Object field = ReflectionUtils.getField(propertyDescriptor, bean);
            if (null != field) {
                values.add("#{" + propertyDescriptor.getName() + "}");
                column.add(StrUtil.humpToUnderline(propertyDescriptor.getName()));
            }
        }


        sql.VALUES(StringUtils.arrayToDelimitedString(column.toArray(new String[column.size()]), ","),
                StringUtils.arrayToDelimitedString(values.toArray(new String[column.size()]), ","));

        return sql.toString();
    }


    public String updateBy(ProviderContext context, Object entity, String... column) {
        SQL sql = new SQL();
        Class<?> aClass = entityType(context);
        sql.UPDATE(tableName(context));

        List<String> sets = new ArrayList<>();

        List<String> fieldNamesList = Arrays.asList(column);

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field propertyDescriptor : declaredFields) {
            propertyDescriptor.setAccessible(true);
            Object field = ReflectionUtils.getField(propertyDescriptor, entity);
            if (!fieldNamesList.contains(propertyDescriptor.getName()) && null != field) {
                sets.add(StrUtil.humpToUnderline(propertyDescriptor.getName()) + " = #{entity." + propertyDescriptor.getName() + "}  ");
            }
        }

        sql.SET(sets.toArray(new String[sets.size()]));

        List<String> conditionSqls = new ArrayList<>();
        Field field = null;
        Object value = null;
        for (String fieldName : column) {
            try {
                field = aClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                value = field.get(entity);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                log.error("{} not found  in {}", fieldName, aClass.getName());
            }
            if (value != null) {
                conditionSqls.add(" " + StrUtil.humpToUnderline(field.getName()) + Option.EQ.getOp() + "#{entity." + field.getName() + "}");
            }
        }
        if (conditionSqls.size() == 0) {
            log.error("update condition not found!");
            return "";
        }

        sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));

        log.info("update sql:{}",sql);
        return sql.toString();
    }

    public String deleteBy(ProviderContext context, Map<String, Object> params) {
        SQL sql = new SQL();
        sql.DELETE_FROM(tableName(context));

        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            conditionSqls.add(buildSql(condition));
        }
        if (conditionSqls.size() == 0) {
            log.error("delete condition not found!");
            return "";
        }

        sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));

        log.info("delete sql:{}",sql);
        return sql.toString();
    }


    public static String tableName(ProviderContext context) {
        return Constant.TABLE_PREFIX + StrUtil.humpToUnderline(entityType(context).getSimpleName());
    }

    public static Class entityType(ProviderContext context) {
        Class<?> mapperType = context.getMapperType();
        return ((Class) ((ParameterizedType) (mapperType.getGenericInterfaces()[0])).getActualTypeArguments()[0]);
    }

    public String buildSql(Condition condition) {
        ConditionSqlHandler conditionSqlHandler = null;
        for (ConditionSqlHandler handler : sqlHandlers) {
            if (handler.supper(condition.getOption())) {
                conditionSqlHandler = handler;
            }
        }
        if (null == conditionSqlHandler) {
            throw new RuntimeException("sql condition conditionSqlHandler is null for option '" + condition.getOption().getOp() + "'");
        }
        String conditionSql = conditionSqlHandler.buildSql(condition);
        return conditionSql;
    }

}