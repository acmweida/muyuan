package com.muyuan.manager.goods.domains.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Feature
 * Description 特征量
 * @Author 2456910384
 * @Date 2022/8/10 11:20
 * @Version 1.0
 */
@Data
public class Feature {

    /**  */
    private Long id;

    /** 特征名称 */
    private String name;

    /** 子特征量ID */
    private String  childId;

    /** 状态 */
    private Integer status;

    /**  */
    private Date createTime;

    /** */
    private String creator;

    /** 属性编码 */
    private String code;

    /**  */
    private Long createBy;


    private List<FeatureValue> values;

}
