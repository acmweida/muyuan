package com.muyuan.goods.domains.model.entity;

import lombok.Data;

/**
 * 类目属性值对象 t_attribute_value
 *
 * @author ${author}
 * @date 2022-12-27T11:15:54.529+08:00
 */
@Data
public class AttributeValue {

    @Data
    static public class Identify {

        private Long id;

        public String value;

        private Long attributeId;

        public Identify(String value, Long attributeId) {
            this.value = value;
            this.attributeId = attributeId;
        }

        public Identify(Long id, String value, Long attributeId) {
            this.id = id;
            this.value = value;
            this.attributeId = attributeId;
        }
    }

    public AttributeValue() {
    }

    public AttributeValue(Long id, Long attributeId, String value) {
        this.id = id;
        this.attributeId = attributeId;
        this.value = value;
    }

    public AttributeValue(Long attributeId, String value) {
        this.attributeId = attributeId;
        this.value = value;
    }

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    private Long attributeId;

    /** $column.columnComment */
    private String value;

}
