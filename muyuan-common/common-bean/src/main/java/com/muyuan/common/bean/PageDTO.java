package com.muyuan.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PageDTO
 * Description PageDTO
 * @Author 2456910384
 * @Date 2022/5/19 11:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PageDTO implements  Paging, Serializable {

    private static final long serialVersionUID = 1457432148568l;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public boolean enablePage() {
        return pageNum != null && pageSize != null && pageNum > 0 && pageSize > 0;
    }

    public void  disablePage() {
        pageNum = pageSize = null;
    }
}
