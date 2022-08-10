package com.muyuan.product.domains.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName GeneralAttribute
 * Description 通用类目属性
 * @Author 2456910384
 * @Date 2022/8/10 11:20
 * @Version 1.0
 */
@Data
public class GeneralAttribute {

    /**  */
    private Long id;

    private Long parentId;

    /**
     * 页面展示类型
     */
    private int htmlType;

    /** 属性名称 */
    private String name;

    private Integer status;

    /** 属性编码 */
    private String code;

    /**  */
    private Long createBy;

    /**  */
    private String creator;

    /** 创建时间 */
    private Date createTime;
}
