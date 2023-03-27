package com.muyuan.goods.infrastructure.repo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyuan.common.mybatis.common.BaseDO;
import lombok.Builder;
import lombok.Data;


/**
 * 商品类目属性对象 t_attribute
 *
 * @author ${author}
 * @date 2022-06-23T10:46:02.101+08:00
 */
@Data
@Builder
@TableName("t_attribute")
public class AttributeDO extends BaseDO {

    /**  */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 页面展示类型
     */
    private int htmlType;

    /** 属性名称 */
    private String name;

    /** 商品分类Code */
    private Long categoryCode;

    /** 属性编码 */
    private String code;

    /** 属性类型 转换为二进制 1:公共属性 10:销售属性 100:关键属性 1000:非关键属性 type值为类型的和 */
    private Integer type;

    /**
     * 输入类型
     */
    private Integer inputType;

    private Long valueReference;

    private Integer valueType;

}
