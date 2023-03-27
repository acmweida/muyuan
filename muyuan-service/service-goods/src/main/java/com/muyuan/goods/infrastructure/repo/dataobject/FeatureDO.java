package com.muyuan.goods.infrastructure.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Data;


/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-12-29T16:35:53.035+08:00
 */
@Data
@TableName("t_feature")
public class FeatureDO  extends BaseDO {

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

}
