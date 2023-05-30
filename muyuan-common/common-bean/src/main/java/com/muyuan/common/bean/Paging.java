package com.muyuan.common.bean;

/**
 * @ClassName Paging 接口
 * Description 分页接口
 * @Author 2456910384
 * @Date 2022/6/1 9:15
 * @Version 1.0
 */
public interface Paging {

    Integer getPageNum();

    Integer getPageSize();

    boolean enablePage();

    void disablePage();
}
