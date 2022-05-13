package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlBuilder {

    private Class target;

    private List<Condition> conditions;

    private String[] columns;

    public SqlBuilder() {
        conditions = new ArrayList<>();
    }

    public SqlBuilder(Class target) {
        this.target = target;
        conditions = new ArrayList<>();
    }

    public boolean valid(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return false;
        }
        return true;
    }

    public boolean valid(Object... value) {
        if (ObjectUtils.isEmpty(value)) {
            return false;
        }
        return true;
    }

    public SqlBuilder select(String column) {
        return select(new String[]{column});
    }

    public SqlBuilder select(String ... columns) {
        this.columns = columns;
        return this;
    }

    private void buildCondition(String field,Option option,Object value) {
        Condition condition = new Condition();
        condition.setField(field);
        condition.setOption(option);
        condition.setValue(value);
        conditions.add(condition);
    }

    public SqlBuilder lt(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.LT,value);
        return this;
    }

    public SqlBuilder lte(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.LTE,value);
        return this;
    }

    public SqlBuilder gt(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.GT,value);
        return this;
    }

    public SqlBuilder gte(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.GTE,value);
        return this;
    }

    public SqlBuilder eq(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.EQ,value);
        return this;
    }

    public SqlBuilder in(String field,Object... value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.IN,value);
        return this;
    }

    public SqlBuilder or() {
        Condition condition = new Condition();
        condition.setOption(Option.OR);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder notEq(String field,Object value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.UNEQ,value);
        return this;
    }

    public SqlBuilder and() {
        Condition condition = new Condition();
        condition.setOption(Option.AND);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder in(String field,List value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.IN,value);
        return this;
    }

    public SqlBuilder in(String field, int[] value) {
        if (!valid(value)) {
            return this;
        }
        buildCondition(field,Option.IN,value);
        return this;
    }

    public SqlBuilder page(Page page) {
        Condition condition = new Condition();
        condition.setField(Constant.PAGE_FIELD);
        condition.setOption(Option.PAGE);
        condition.setValue(page);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder orderByAsc(String... fields) {
        return orderBy(true,fields);
    }

    public SqlBuilder orderByDesc(String... fields) {
        return orderBy(false,fields);
    }

    private SqlBuilder orderBy(boolean up,String... fields) {
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
        condition.setValue(StringUtils.join(columns,",")+ (up ? " asc" : " desc") );
        conditions.add(condition);
        return this;
    }

    public Map build() {
        Map params = new HashMap();
        for (Condition condition : conditions) {
            if (condition.getOption() == Option.PAGE) {
                params.put(Constant.PAGE_FIELD,condition.getValue());
                continue;
            }
            if (condition.getOption() == Option.OR) {
                continue;
            }
            String column = condition.getField();
            params.put(column,condition.getValue());
        }
        params.put(Constant.CONDITION,conditions);
        if (null == columns && null != target) {
            List<String> column = new ArrayList<>();
            Field[] declaredFields = target.getDeclaredFields();
            for (Field propertyDescriptor : declaredFields) {
                column.add(StrUtil.humpToUnderline(propertyDescriptor.getName()) + " as "+propertyDescriptor.getName());
            }
            String[] columns = new String[column.size()];
            column.toArray(columns);
            params.put(Constant.COLUMN,columns);
        } else {
            params.put(Constant.COLUMN,columns);
        }
        return params;
    }






}
