package com.muyuan.manager.product.domains.dto;

import com.muyuan.common.core.bean.BaseDTO;
import com.muyuan.manager.product.domains.model.Feature;
import lombok.Data;

import java.util.Date;


/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@Data
public class FeatureDTO extends BaseDTO<FeatureDTO, Feature> {

    /** $column.columnComment */
    private Long id;

    /** 属性名称 */
    private String name;

    /** HTML元素类型 */
    private Long htmlType;

    /** 父属性ID */
    private Long parentId;

    /** 状态 */
    private Long status;

    /** $column.columnComment */
    private Date createTime;

    /** $column.columnComment */
    private Long creator;

    /** 属性编码 */
    private String code;


}
