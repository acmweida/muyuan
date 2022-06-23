package com.muyuan.product.domains.model;

import lombok.Data;

import java.util.Date;


/**
 * 商品分类属性对象 t_category_attribute
 *
 * @author ${author}
 * @date 2022-06-23T10:46:02.101+08:00
 */
@Data
public class CategoryAttribute {

    /**  */
    private Long id;

    /** 属性名称 */
    private String name;

    /** 商品分类ID */
    private Long categoryId;

    /** 属性类型 1:关键属性 2:销售属性 3:非关键属性 */
    private Long type;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /**  */
    private Long createBy;

    /**  */
    private String creator;

    /**  */
    private Long updateBy;

    /**  */
    private String updater;



}
