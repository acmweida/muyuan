package com.muyuan.common.mybatis.jdbc;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.muyuan.common.bean.Page;
import com.muyuan.common.mybatis.common.Constant;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlParamsBuilder<T> {

    private Page pageInfo;

    private boolean page = false;

    private Map<String, Object> params = new HashMap<>();


    public SqlParamsBuilder() {
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

    public SqlParamsBuilder<T> param(String field, Object value) {
        if (!valid(value)) {
            return this;
        }
        params.put(field, value);
        return this;
    }

    public SqlParamsBuilder<T> param(SFunction<T,?> field, Object value) {

        LambdaMeta meta = LambdaUtils.extract(field);
        String fieldName = PropertyNamer.methodToProperty(meta.getImplMethodName());

        if (!valid(value)) {
            return this;
        }
        params.put(fieldName, value);
        return this;
    }

    public SqlParamsBuilder<T> param(String field, Object... value) {
        if (!valid(value)) {
            return this;
        }
        params.put(field, value);
        return this;
    }


    public SqlParamsBuilder<T> in(String field, List value) {
        if (!valid(value)) {
            return this;
        }
        params.put(field, value);
        return this;
    }

    public SqlParamsBuilder<T> in(String field, int[] value) {
        if (!valid(value)) {
            return this;
        }
        params.put(field, value);
        return this;
    }

    public SqlParamsBuilder<T> page(Page pageInfo) {
        if (null != pageInfo) {
            this.pageInfo = pageInfo;
            page = true;
        }
        return this;
    }

    public SqlParamsBuilder<T> page(int pageNum, int pageSize) {
        return page(Page.builder().pageNum(pageNum).pageSize(pageSize).build());
    }

    public Map<String, Object> build() {
        buildPage(params);
        return params;
    }


    private void buildPage(Map<String, Object> params) {
        if (page) {
            params.put(Constant.PAGE_FIELD, pageInfo);
        }
    }

}
