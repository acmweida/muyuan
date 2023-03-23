package com.muyuan.goods.infrastructure.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


/**
 * 商品分类对象 t_category
 *
 * @author ${author}
 * @date 2022-12-16T11:54:09.147+08:00
 */
@Data
@TableName("t_category")
public class CategoryDO  {

    @TableId(type = IdType.AUTO)
    /** 主键 */
    private Long id;

    /** 父类ID */
    private Long parentId;

    /** 分类名称 */
    private String name;

    /** 晨级 */
    private Long level;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 产品编码 */
    private Long code;

    /** 层级路径 */
    private String ancestors;

    /** $column.columnComment */
    private String logo;

    /** $column.columnComment */
    private Long productCount;

    /** 0-上架 1-下架 2-删除 */
    private Long status;

    /** $column.columnComment */
    private Long orderNum;

    /** 创建人ID */
    private Long createBy;

    /** 创建人 */
    private String creator;

    /** 更新人ID */
    private Long updateBy;

    /** 更新人
 */
    private String updater;

    /** 是否叶子节点 */
    private String leaf;

    /** 子节点数量 */
    private Long subCount;


}
