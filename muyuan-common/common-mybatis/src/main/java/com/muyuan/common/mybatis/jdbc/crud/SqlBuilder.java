package com.muyuan.common.mybatis.jdbc.crud;

import com.muyuan.common.core.util.StrUtil;
import com.muyuan.common.mybatis.jdbc.page.Page;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlBuilder {

    private Class target;

    private List<Condition> conditions;

    private String[] columns;

    public SqlBuilder(Class target) {
        this.target = target;
        conditions = new ArrayList<>();
    }

    public SqlBuilder select(String column) {
        return select(new String[]{column});
    }

    public SqlBuilder select(String ... columns) {
        this.columns = columns;
        return this;
    }

    public SqlBuilder eq(String field,Object value) {
        Condition condition = new Condition();
        condition.setField(field);
        condition.setOption(Option.EQ);
        condition.setValue(value);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder in(String field,Object... params) {
        Condition condition = new Condition();
        condition.setField(field);
        condition.setOption(Option.IN);
        condition.setValue(params);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder or() {
        Condition condition = new Condition();
        condition.setOption(Option.OR);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder and() {
        Condition condition = new Condition();
        condition.setOption(Option.AND);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder in(String field,List params) {
        Condition condition = new Condition();
        condition.setField(field);
        condition.setOption(Option.IN);
        condition.setValue(params);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder in(String field, int[] params) {
        Condition condition = new Condition();
        condition.setField(field);
        condition.setOption(Option.IN);
        condition.setValue(params);
        conditions.add(condition);
        return this;
    }

    public SqlBuilder page(int pageNum,int pageSize) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page(page);
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
            String column = StrUtil.humpToUnderline(condition.getField());
            params.put(column,condition.getValue());
        }
        params.put(Constant.CONDITION,conditions);
        if (null == columns) {
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
