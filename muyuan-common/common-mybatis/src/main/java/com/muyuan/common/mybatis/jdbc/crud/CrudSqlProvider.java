package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.constant.GlobalConst;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.crud.impl.EqConditionSqlHandler;
import com.muyuan.common.mybatis.jdbc.crud.impl.InConditionSqlHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
@Slf4j
public class CrudSqlProvider {

    private static ConcurrentHashMap<Option, ConditionSqlHandler> sqlHandlers = new ConcurrentHashMap<>();

    static {
        sqlHandlers.put(Option.EQ,new EqConditionSqlHandler());
        sqlHandlers.put(Option.IN,new InConditionSqlHandler());
    }

    public String selectOne(Map<String,Object> params,ProviderContext context) {
        SQL sql = new SQL();

        String[] column = (String[]) params.get(Constant.COLUMN);
        sql.SELECT(column).FROM(tableName(context));
        String conditionSql ;
        List<String> conditionSqls = new ArrayList<>();
        List<Condition> conditions = (List<Condition>) params.get(Constant.CONDITION);
        for (Condition condition : conditions) {
            Option option = condition.getOption();
            if (option == Option.PAGE) {
                continue;
            }
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

    public String selectList(Map<String,Object> params,ProviderContext context) {
        SQL sql = new SQL();

        List<String> orderBY = new ArrayList();
        String[] column = (String[]) params.get(Constant.COLUMN);
        sql.SELECT(column).FROM(tableName(context));
        String conditionSql ;
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

        if (!ObjectUtils.isEmpty(orderBY)) {
            sql.ORDER_BY(StringUtils.arrayToDelimitedString(orderBY.stream().toArray(),"m"));
        }

        return sql.toString();
    }

    public String insert(Object bean,ProviderContext context) {
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


        sql.VALUES(StringUtils.arrayToDelimitedString(column.toArray(new String[column.size()]),","),
                StringUtils.arrayToDelimitedString(values.toArray(new String[column.size()]),",") );
        return sql.toString();
    }


    public String updateById(ProviderContext context,Object bean) {
        return updateBy(context,bean, GlobalConst.ID);
    }

    public String updateBy(ProviderContext context,Object bean,String... fieldNamesArr) {
        SQL sql = new SQL();
        Class<?> aClass =entityType(context);
        sql.UPDATE(tableName(context));

        List<String> sets = new ArrayList<>();

        List<String> fieldNamesList = Arrays.asList(fieldNamesArr);

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field propertyDescriptor : declaredFields) {
            propertyDescriptor.setAccessible(true);
            Object field = ReflectionUtils.getField(propertyDescriptor, bean);
            if ( !fieldNamesList.contains(propertyDescriptor.getName())  &&  null != field) {
                sets.add(StrUtil.humpToUnderline(propertyDescriptor.getName()) + " = #{" + propertyDescriptor.getName() + "}  ");
            }
        }

        sql.SET(sets.toArray(new String[sets.size()]));

        List<String> conditionSqls = new ArrayList<>();
        Field field = null;
        Object value = null;
        for (String fieldName : fieldNamesArr) {
            try {
                field = aClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                value = field.get(bean);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                log.error("{} not found  in {}",fieldName,aClass.getName());
            }
            if (value != null) {
                conditionSqls.add(" " + StrUtil.humpToUnderline(field.getName()) + Option.EQ.getOp() + "#{" + field.getName() + "}");
            }
        }
        if (conditionSqls.size() == 0) {
            log.error("update condition not found!");
            return "";
        }

        sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));

        return sql.toString();
    }

    public String deleteBy(ProviderContext context,Object bean,String... fieldNamesArr) {
        SQL sql = new SQL();
        Class<?> aClass =entityType(context);
        sql.DELETE_FROM(tableName(context));

        List<String> conditionSqls = new ArrayList<>();
        Field field = null;
        Object value = null;
        for (String fieldName : fieldNamesArr) {
            try {
                field = aClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                value = field.get(bean);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                log.error("{} not found  in {}",fieldName,aClass.getName());
            }
            if (value != null) {
                if (field.getType().isArray() || value instanceof Collections) {
                    conditionSqls.add(" " + StrUtil.humpToUnderline(field.getName()) + Option.IN.getOp() + "#{" + field.getName() + "}");
                } else {
                    conditionSqls.add(" " + StrUtil.humpToUnderline(field.getName()) + Option.EQ.getOp() + "#{" + field.getName() + "}");
                }
            }
        }
        if (conditionSqls.size() == 0) {
            log.error("update condition not found!");
            return "";
        }

        sql.WHERE(conditionSqls.toArray(new String[conditionSqls.size()]));

        return sql.toString();
    }


    public String deleteByIds(ProviderContext context,String... ids) {
        SQL sql = new SQL();
        sql.DELETE_FROM(tableName(context));
        sql.WHERE( sqlHandlers.get(Option.IN).buildSql(new Condition("id",ids,Option.IN)));
        return sql.toString();
    }


    public static String tableName(ProviderContext context) {
        return Constant.TABLE_PREFIX+StrUtil.humpToUnderline(entityType(context).getSimpleName());
    }

    public static Class entityType(ProviderContext context) {
        Class<?> mapperType = context.getMapperType();
        return ((Class)((ParameterizedType)(mapperType.getGenericInterfaces()[0])).getActualTypeArguments()[0]);

    }

}