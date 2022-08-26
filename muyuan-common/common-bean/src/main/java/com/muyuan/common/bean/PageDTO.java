package com.muyuan.common.bean;

import lombok.Data;

/**
 * @ClassName BaseDTO
 * Description BaseDTO
 * @Author 2456910384
 * @Date 2022/5/19 11:00
 * @Version 1.0
 */
@Data
public abstract class PageDTO implements  Paging {

    private int pageNum = 1;

    private int pageSize= 10;

}
