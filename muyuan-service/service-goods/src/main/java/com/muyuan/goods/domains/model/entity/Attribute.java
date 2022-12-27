package com.muyuan.goods.domains.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Date;
import java.util.List;


/**
 * 商品类目属性对象 t_attribute
 *
 * @author ${author}
 * @date 2022-06-23T10:46:02.101+08:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {

    @Data
    public static class Identify {

        private Long id;

        private Long categoryCode;

        private String name;

        public Identify(Long categoryCode, String name) {
            this.categoryCode = categoryCode;
            this.name = name;
        }

        public Identify(Long id, Long categoryCode, String name) {
            this.id = id;
            this.categoryCode = categoryCode;
            this.name = name;
        }
    }


    /**
     *
     */
    private Long id;

    /**
     * 页面展示类型
     */
    private Integer htmlType;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 商品分类Code
     */
    private Long categoryCode;

    /**
     * 属性编码
     */
    private String code;

    /**
     * 属性类型 转换为二进制 1:公共属性 10:销售属性 100:关键属性 1000:非关键属性 type值为类型的和
     */
    private Integer type;

    /**
     * 输入类型
     */
    private Integer inputType;

    /**
     * 输入类型为选择时 值引用
     */
    private Long valueReference;

    /**
     * 输入类型为选择时，值来源类型  0-特征量 1-属性值
     */
    private Integer valueType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    private String creator;

    /**
     *
     */
    private Long updateBy;

    /**
     *
     */
    private String updater;


    public Boolean isType(int type) {
        return ObjectUtils.isNotEmpty(this.type) && (this.type & type) > 0;
    }

    private List<AttributeValue> attributeValues;

}
