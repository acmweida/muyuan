package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.common.Constant;
import com.muyuan.common.mybatis.jdbc.crud.impl.CollectionConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.impl.SampleConditionSqlHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.muyuan.common.mybatis.common.Constant.*;


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
            if (option.isParma()) {
                conditionSqls.add(buildSql(condition));
            } else if (option == Option.OR || option == Option.AND) {
                sql.WHERE(conditionSqls.toArray(new String[0]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else {
                    sql.AND();
                }
            }
        }
        if (!conditions.isEmpty()) {
            sql.WHERE(conditionSqls.toArray(new String[0]));
            sql.LIMIT(1);
        } else {
            log.error("sql condition is empty");
            return "";
        }

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

            if (option.isParma()) {
                conditionSqls.add(buildSql(condition));
            } else if (option == Option.OR || option == Option.AND) {
                sql.WHERE(conditionSqls.toArray(new String[0]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else {
                    sql.AND();
                }
            } else if (option == Option.ORDER) {
                orderBY.add((String) condition.getValue());
            }
        }

        if (!conditions.isEmpty()) {
            sql.WHERE(conditionSqls.toArray(new String[0]));
        }

        if (!ObjectUtils.isEmpty(orderBY)) {
            sql.ORDER_BY(StringUtils.arrayToDelimitedString(orderBY.toArray(), ","));
        }

        return sql.toString();
    }


    public String count(Map<String, Object> params, ProviderContext context) {
        SQL sql = new SQL();

        sql.SELECT("count(1) ").FROM(tableName(context));
        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            Option option = condition.getOption();
            if (option.isParma()) {
                conditionSqls.add(buildSql(condition));
            } else if (option == Option.OR || option == Option.AND) {
                sql.WHERE(conditionSqls.toArray(new String[0]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else {
                    sql.AND();
                }
            }
        }
        if (!conditions.isEmpty()) {
            sql.WHERE(conditionSqls.toArray(new String[0]));
            sql.LIMIT(1);
        } else {
            log.error("sql condition is empty");
            return "";
        }

        return sql.toString();
    }

    public String insert(Object bean, ProviderContext context) {
        SQL sql = new SQL();
        sql.INSERT_INTO(tableName(context));

        List<String> values = new ArrayList<>();
        List<String> column = new ArrayList<>();

        Class target = entityType(context);
        String[] exclude = excludeColumn(target);

        Field[] declaredFields = target.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!isColumn(field,exclude)) {
                continue;
            }
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, bean);
            if (!ObjectUtils.isEmpty(value)) {
                values.add("#{" + field.getName() + "}");
                column.add(StrUtil.humpToUnderline(field.getName()));
            }
        }


        sql.VALUES(StringUtils.arrayToDelimitedString(column.toArray(new String[0]), ","),
                StringUtils.arrayToDelimitedString(values.toArray(new String[column.size()]), ","));

        return sql.toString();
    }

    private boolean isColumn(Field field,String[] exclude) {
        return JDBC_TYPE.contains(field.getType())
                && !Modifier.isStatic(field.getModifiers())
                && !ArrayUtils.contains(exclude, field.getName());
    }

    public String update(Map<String, Object> param, ProviderContext context) {
        SQL sql = new SQL();
        sql.UPDATE(tableName(context));

        List<String> sets = new ArrayList<>();

        List<Condition> updateCondition = (List<Condition>) param.get(Constant.UPDATE);
        for (Condition condition : updateCondition) {
            sets.add(buildSql(condition));
        }
        sql.SET(sets.toArray(new String[0]));

        List<Condition> whereCondition = (List<Condition>) param.get(Constant.CONDITION);

        List<String> conditionSqls = new ArrayList<>();
        for (Condition condition : whereCondition) {
            Option option = condition.getOption();
            if (option.isParma()) {
                conditionSqls.add(buildSql(condition));
            } else if (option == Option.OR || option == Option.AND) {
                sql.WHERE(conditionSqls.toArray(new String[0]));
                conditionSqls.clear();
                if (option == Option.OR) {
                    sql.OR();
                } else {
                    sql.AND();
                }
            }
        }

        if (conditionSqls.size() == 0) {
            log.error("where condition not found!");
            return "";
        }

        sql.WHERE(conditionSqls.toArray(new String[0]));

        return sql.toString();
    }


    public String updateBy(ProviderContext context, Object entity, String... byColumn) {
        Class aClass = entityType(context);
        String[] exclude = excludeColumn(aClass);
        List<String> sets = new ArrayList<>();

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, entity);
            if (
                    !ObjectUtils.isEmpty(value) &&
                    isColumn(field,exclude)) {
                sets.add(field.getName());
            }
        }

        return updateColumnBy(context,entity,sets.toArray(new String[0]),byColumn);
    }

    public String updateColumnBy(ProviderContext context, Object entity, String[] column, String... byColumn) {
        SQL sql = new SQL();
        Class aClass = entityType(context);
        sql.UPDATE(tableName(context));

        String[] exclude = excludeColumn(aClass);
        List<String> sets = new ArrayList<>();

        List<String> fieldNamesList = Arrays.asList(byColumn);

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object value = ReflectionUtils.getField(field, entity);
            if (!fieldNamesList.contains(field.getName())
                    && ArrayUtils.contains(column, field.getName())
                    && !ObjectUtils.isEmpty(value)
            ) {
                if (isColumn(field,exclude)) {
                    sets.add(StrUtil.humpToUnderline(field.getName()) + " = #{entity." + field.getName() + "}  ");
                }
            }
        }

        sql.SET(sets.toArray(new String[0]));

        List<String> conditionSqls = new ArrayList<>();
        Field field = null;
        Object value = null;
        for (String fieldName : byColumn) {
            try {
                field = aClass.getDeclaredField(fieldName);
                if (!jdbcType(field.getType())) {
                    continue;
                }
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

        sql.WHERE(conditionSqls.toArray(new String[0]));

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

        sql.WHERE(conditionSqls.toArray(new String[0]));

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
            if (handler.suppert(condition.getOption())) {
                conditionSqlHandler = handler;
            }
        }
        if (null == conditionSqlHandler) {
            throw new RuntimeException("sql condition conditionSqlHandler is null for option '" + condition.getOption().getOp() + "'");
        }
        String conditionSql = conditionSqlHandler.buildSql(condition);
        return conditionSql;
    }

    private boolean jdbcType(Class c) {
        return JDBC_TYPE.contains(c);
    }

    private String[] excludeColumn(Class target) {
        ColumnExclude columnExclude = AnnotationUtils.findAnnotation(target, ColumnExclude.class);
        String[] exclude = DEFAULT_EXCLUDE_COLUMN;
        if (null != columnExclude) {
            exclude = ArrayUtils.addAll(columnExclude.value(), exclude);
        }
        return exclude;
    }

}