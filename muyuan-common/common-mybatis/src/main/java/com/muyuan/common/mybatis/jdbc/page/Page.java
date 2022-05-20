package com.muyuan.common.mybatis.jdbc.page;

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
}
