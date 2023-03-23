package com.muyuan.common.mybatis.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseDO {

    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    /** 提交人名称 */
    private Long creator;

    @TableField(fill = FieldFill.UPDATE)
    /** 更新人名称 */
    private Long updater;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @TableField(fill = FieldFill.INSERT)
    /** 创建时间 */
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    /** 更新者 */
    private String updateBy;

    @TableField(fill = FieldFill.UPDATE)
    /** 更新时间 */
    private Date updateTime;

}
