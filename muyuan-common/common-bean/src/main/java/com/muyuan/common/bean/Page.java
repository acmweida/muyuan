package com.muyuan.common.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Page<T> implements Serializable {

    private int pageNum;

    private int pageSize;

    private int total;

    private int totalPage;

    private List<T> rows;

    public static Page newInstance(Paging paging) {
        return Page.builder().pageNum(paging.getPageNum()).pageSize(paging.getPageSize()).build();
    }

    public static <T> Page<T> copy(Page paging,List<T> rows) {
        return Page.<T>builder().pageNum(paging.pageNum)
                .pageSize(paging.pageSize)
                .total(paging.total)
                .totalPage(paging.totalPage)
                .rows(rows)
                .build();
    }
}
