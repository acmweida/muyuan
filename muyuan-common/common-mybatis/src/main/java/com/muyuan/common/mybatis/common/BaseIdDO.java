package com.muyuan.common.mybatis.common;

import com.baomidou.mybatisplus.annotation.TableId;

public abstract class BaseIdDO {

    @TableId
    private long id;

}
