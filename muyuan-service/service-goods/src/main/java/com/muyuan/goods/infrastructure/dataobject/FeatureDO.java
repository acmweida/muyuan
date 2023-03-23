package com.muyuan.goods.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 */
@Data
@TableName("t_feature")
public class FeatureDO  {

    @TableId(type = IdType.AUTO)
    /** $column.columnComment */
    private Long id;

    /** 属性名称 */
    private String name;

    /** $column.columnComment */
    private String parentId;

    /** 是否叶子节点 0-是 1-否 */
    private Integer leaf;

    /** 状态 */
    private Long status;

    /** $column.columnComment */
    private String creator;

    /** $column.columnComment */
    private Long createBy;

    /** $column.columnComment */
    private Date createTime;


}
