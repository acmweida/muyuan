package com.muyuan.common.mybatis.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseDO {

    @TableId
    protected Long id;

    @TableField(fill = FieldFill.INSERT)
    /** 提交人名称 */
    protected Long creator;

    @TableField(fill = FieldFill.UPDATE)
    /** 更新人名称 */
    protected Long updater;

    @TableField(fill = FieldFill.INSERT)
    protected String createBy;

    @TableField(fill = FieldFill.INSERT)
    /** 创建时间 */
    protected Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    /** 更新者 */
    protected String updateBy;

    @TableField(fill = FieldFill.UPDATE)
    /** 更新时间 */
    protected Date updateTime;

}
