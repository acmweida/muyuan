package com.muyuan.manager.goods.domains.dto;

import com.muyuan.common.bean.BaseDTO;
import com.muyuan.manager.goods.domains.model.Feature;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 通用特征量对象 t_feature
 *
 * @author ${author}
 * @date 2022-08-16T13:58:01.607+08:00
 */
@Data
@Builder
public class FeatureDTO extends BaseDTO<FeatureDTO, Feature> {

    /**  */
    private Long id;

    /** 属性名称 */
    @NotBlank(message = "特征量名称不能为空")
    private String name;

    /** 状态 */
    private Long status;

    /** 编码 */
    @NotBlank(message = "特征量编码不能为空")
    private String code;

}
