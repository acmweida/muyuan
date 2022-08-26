package com.muyuan.common.bean;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Page<T> {

    private int pageNum;

    private int pageSize;

    private int total;

    private int totalPage;

    private List<T> rows;

    public static Page newInstance(Paging paging) {
        return Page.builder().pageNum(paging.getPageNum()).pageSize(paging.getPageSize()).build();
    }
}
