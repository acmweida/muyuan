package com.muyuan.common.mybatis.common;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public abstract class BaseIdDO {

    @TableId
    private Long id;

}
