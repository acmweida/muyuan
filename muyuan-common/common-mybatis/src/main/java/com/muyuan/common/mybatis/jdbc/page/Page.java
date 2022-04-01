package com.muyuan.common.mybatis.jdbc.page;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    private int pageNum;

    private int pageSize;

    private int total;

    private int totalPage;

    private List<T> data;
}
