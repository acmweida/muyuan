package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.cache.localcache.LocalCacheService;
import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.common.Constant;
import com.muyuan.common.mybatis.common.SqlType;
import com.muyuan.common.mybatis.jdbc.page.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.muyuan.common.mybatis.common.Constant.*;

public class SqlBuilder {

    private Class target;

    private List<Condition> conditions;

    private List<Condition> updates;

    private String[] columns;

    private Page pageInfo;

    private boolean page = false;

    private SqlType sqlType = SqlType.SELECT;

    public SqlBuilder() {
        conditions = new ArrayList<>();
        updates = new ArrayList<>();
    }

    public SqlBuilder(Class target) {
        this.target = target;
        conditions = new ArrayList<>();
        updates = new ArrayList<>();
    }

    private boolean valid(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return false;
        }
        return true;
    }

    private boolean valid(Object... value) {
        if (ObjectUtils.isEmpty(value)) {
            return false;
        }
        return true;
    }

    public SqlBuilder set(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildUpdate(field, UPDATE_PREFIX+ field, Option.EQ, value);
        return this;
    }

    public SqlBuilder select(String column) {
        return select(new String[]{column});
    }

    public SqlBuilder select(String... columns) {
        this.columns = columns;
        return this;
    }

    public SqlBuilder unselect(String column) {
        return select(new String[]{column});
    }

    public SqlBuilder unselect(String... columns) {
        this.columns = columns;
        return this;
    }


    private void buildCondition(String field, Option option, Object value) {
        buildCondition(field, field, option, value);
    }

    private void buildUpdate(String field, String expression, Option option, Object value) {
        Condition update = new Condition();
        update.setField(field);
        update.setOption(option);
        update.setValue(value);
        update.setExpression(expression);
        updates.add(update);
    }

    private void buildCondition(String field, String expression, Option option, Object value) {
        Condition condition = new Condition();
        condition.setField(field);
        condition.setOption(option);
        condition.setValue(value);
        condition.setExpression(expression);
        conditions.add(condition);
    }

    public SqlBuilder lt(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Constant.LT_PREFIX + field, Option.LT, value);
        return this;
    }

    public SqlBuilder lte(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Constant.LTE_PREFIX + field, Option.LTE, value);
        return this;
    }

    public SqlBuilder gt(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Constant.GT_PREFIX + field, Option.GT, value);
        return this;
    }

    public SqlBuilder gte(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Constant.GTE_PREFIX + field, Option.GTE, value);
        return this;
    }

    public SqlBuilder like(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Option.LIKE, value);
        return this;
    }

    public SqlBuilder eq(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Option.EQ, value);
        return this;
    }

    public SqlBuilder in(String field, Object... value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Option.IN, value);
        return this;
    }

    public SqlBuilder or() {
        Condition condition = new Condition();
        condition.setOption(Option.OR);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder notEq(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Option.UNEQ, value);
        return this;
    }

    public SqlBuilder and() {
        Condition condition = new Condition();
        condition.setOption(Option.AND);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder in(String field, List value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Option.IN, value);
        return this;
    }

    public SqlBuilder in(String field, int[] value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field, Option.IN, value);
        return this;
    }

    public SqlBuilder page(Page<Object> pageInfo) {
        if (null != pageInfo) {
            this.pageInfo = pageInfo;
            page = true;
        }
        return this;
    }

    public SqlBuilder page(int pageNum, int pageSize) {
        return page(Page.builder().pageNum(pageNum).pageSize(pageSize).build());
    }

    public SqlBuilder orderByAsc(String... fields) {
        return orderBy(true, fields);
    }

    public SqlBuilder orderByDesc(String... fields) {
        return orderBy(false, fields);
    }

    private SqlBuilder orderBy(boolean up, String... fields) {
        if (!valid(fields)) {
            return this;
        }
        Condition condition = new Condition();
        condition.setField(Constant.ORDERBY);
        condition.setOption(Option.ORDER);
        List<String> columns = new ArrayList(fields.length);
        for (String field : fields) {
            columns.add(StrUtil.humpToUnderline(field));
        }
        condition.setValue(StringUtils.join(columns, ",") + (up ? " asc" : " desc"));
        conditions.add(condition);
        return this;
    }

    public Map<String,Object> build() {
        Map<String, Object> params = new HashMap();
        sqlType();
        switch (sqlType) {
            case SELECT:
                buildPage(params);
                buildSelectColumn(params);
                break;
            case UPDATE:
                buildUpdateCondition(params);
        }
        buildWhereCondition(params);
        return params;
    }

    private void sqlType() {
        if (ObjectUtils.isNotEmpty(updates)) {
            sqlType = SqlType.UPDATE;
        }
    }

    private void  buildWhereCondition(Map<String,Object> params) {
        for (Condition condition : conditions) {
            if (condition.getOption().isParma()) {
                String column = condition.getExpression();
                params.put(column, condition.getValue());
            }
        }
        params.put(Constant.CONDITION, conditions);
    }

    private void  buildUpdateCondition(Map<String,Object> params) {
        params.put(UPDATE, updates);
        for (Condition condition : updates) {
            if (condition.getOption().isParma()) {
                String column = condition.getExpression();
                params.put(column, condition.getValue());
            }
        }
    }

    private void buildPage(Map<String,Object> params) {
        if (page) {
            params.put(Constant.PAGE_FIELD, pageInfo);
        }
    }

    public void buildSelectColumn(Map<String,Object> params) {
        if (null == columns && null != target) {
            String[] columns = (String[]) LocalCacheService.getInstance().getAndUpdate(target.getName(),
                    () -> {
                        List<String> column = new ArrayList<>();
                        Field[] declaredFields = target.getDeclaredFields();
                        ColumnExclude columnExclude = (ColumnExclude) target.getDeclaredAnnotation(ColumnExclude.class);
                        String[] exclude = DEFAULT_EXCLUDE_COLUMN;
                        if (null != columnExclude) {
                            exclude = ArrayUtils.addAll(columnExclude.value(),exclude);
                        }

                        for (Field propertyDescriptor : declaredFields) {
                            if (JDBC_TYPE.contains(propertyDescriptor.getType())
                                    && !Modifier.isStatic(propertyDescriptor.getModifiers())
                                    && !serialVersionUID.equals(propertyDescriptor.getName())
                                    && !ArrayUtils.contains(exclude, propertyDescriptor.getName())
                            ) {
                                column.add(StrUtil.humpToUnderline(propertyDescriptor.getName()) + " as " + propertyDescriptor.getName());
                            }
                        }
                        String[] columnsNew = new String[column.size()];
                        column.toArray(columnsNew);
                        return columnsNew;
                    }
            );

            params.put(Constant.COLUMN, columns);
        } else {
            params.put(Constant.COLUMN, columns);
        }
    }


}
