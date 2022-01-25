package com.muyuan.common.repo.jdbc.page;

import lombok.Data;

import java.util.List;

@Data
public class Page {

    private int pageNum;

    private int pageSize;

    private int total;

    private int totalPage;

    private List data;
}
